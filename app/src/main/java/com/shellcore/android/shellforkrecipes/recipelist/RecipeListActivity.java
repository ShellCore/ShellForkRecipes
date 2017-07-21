package com.shellcore.android.shellforkrecipes.recipelist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.shellcore.android.shellforkrecipes.R;
import com.shellcore.android.shellforkrecipes.ShellForkRecipesApplication;
import com.shellcore.android.shellforkrecipes.db.entities.Recipe;
import com.shellcore.android.shellforkrecipes.recipelist.adapters.OnItemClickListener;
import com.shellcore.android.shellforkrecipes.recipelist.adapters.RecipeListAdapter;
import com.shellcore.android.shellforkrecipes.recipelist.di.RecipeListComponent;
import com.shellcore.android.shellforkrecipes.recipelist.ui.RecipeListView;
import com.shellcore.android.shellforkrecipes.recipemain.ui.RecipeMainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeListActivity extends AppCompatActivity implements RecipeListView, OnItemClickListener {

    // Constants
    public static final int NUM_COLUMNS = 2;

    // Services
    private RecipeListAdapter adapter;
    private RecipeListPresenter presenter;
    private RecipeListComponent component;

    // Components
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rec_list)
    RecyclerView recList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);
        setupToolbar();
        setupInjection();
        setupRecyclerView();
        presenter.onCreate();
        presenter.getRecipes();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipe_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_main:
                navigateToMainScreen();
                break;
            case R.id.action_logout:
                logout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.toolbar)
    public void onToolbarClick() {
        recList.smoothScrollToPosition(0);
    }

    @Override
    public void onFavClick(Recipe recipe) {
        presenter.toggleFavorite(recipe);
    }

    @Override
    public void onItemClick(Recipe recipe) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getSourceUrl()));
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Recipe recipe) {
        presenter.removeRecipe(recipe);
    }

    @Override
    public void setRecipes(List<Recipe> data) {
        adapter.setRecipes(data);
    }

    @Override
    public void recipeUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void recipeDeleted(Recipe recipe) {
        adapter.removeRecipe(recipe);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setupInjection() {
        ShellForkRecipesApplication app = (ShellForkRecipesApplication) getApplication();
        component = app.getRecipeListComponent(this, this, this);
        adapter = getAdapter();
        presenter = getPresenter();
    }

    private void setupRecyclerView() {
        recList.setLayoutManager(new GridLayoutManager(this, NUM_COLUMNS));
        recList.setAdapter(adapter);
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this, RecipeMainActivity.class));
    }

    private void logout() {
        ShellForkRecipesApplication app = (ShellForkRecipesApplication) getApplication();
        app.logout();
    }

    private RecipeListAdapter getAdapter() {
        return component.getAdapter();
    }

    private RecipeListPresenter getPresenter() {
        return component.getPresenter();
    }

}
