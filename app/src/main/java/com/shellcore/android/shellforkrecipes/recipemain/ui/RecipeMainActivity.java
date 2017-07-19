package com.shellcore.android.shellforkrecipes.recipemain.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.shellcore.android.shellforkrecipes.R;
import com.shellcore.android.shellforkrecipes.db.entities.Recipe;
import com.shellcore.android.shellforkrecipes.libs.base.ImageLoader;
import com.shellcore.android.shellforkrecipes.recipemain.RecipeMainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeMainActivity extends AppCompatActivity implements RecipeMainView {

    // Variables
    private Recipe currentRecipe;

    // Services
    private RecipeMainPresenter presenter;
    private ImageLoader imageLoader;

    // Components
    @BindView(R.id.imgRecipe)
    ImageView imgRecipe;
    @BindView(R.id.btnDismiss)
    ImageButton btnDismiss;
    @BindView(R.id.btnkeep)
    ImageButton btnkeep;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.container)
    RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main);
        ButterKnife.bind(this);

        setupInjection();
        presenter.onCreate();
        presenter.getNextRecipe();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        // TODO
    }


    @OnClick(R.id.btnkeep)
    public void onKeep() {
        if (currentRecipe != null) {
            presenter.saveRecipe(currentRecipe);
        }
    }

    @OnClick(R.id.btnDismiss)
    public void onDismiss() {
        presenter.dismissRecipe();
    }

    @Override
    public void showProgressbar() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void showUIElements() {
        btnDismiss.setVisibility(View.VISIBLE);
        btnkeep.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUIElements() {
        btnDismiss.setVisibility(View.GONE);
        btnkeep.setVisibility(View.GONE);
    }

    @Override
    public void saveAnimation() {
        // TODO
    }

    @Override
    public void dismissAnimation() {
        // TODO
    }

    @Override
    public void onRecipeSaved() {
        Snackbar.make(container, R.string.recipemain_notice_saved, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void setRecipe(Recipe recipe) {
        currentRecipe = recipe;
        imageLoader.load(imgRecipe, recipe.getImageUrl());
    }

    @Override
    public void onGetRecipeError(String error) {
        String errorMsg = String.format(getString(R.string.recipemain_error), error);
        Snackbar.make(container, errorMsg, Snackbar.LENGTH_SHORT)
                .show();
    }

}
