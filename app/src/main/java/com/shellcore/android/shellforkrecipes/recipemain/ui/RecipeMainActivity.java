package com.shellcore.android.shellforkrecipes.recipemain.ui;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.shellcore.android.shellforkrecipes.R;
import com.shellcore.android.shellforkrecipes.ShellForkRecipesApplication;
import com.shellcore.android.shellforkrecipes.db.entities.Recipe;
import com.shellcore.android.shellforkrecipes.libs.base.ImageLoader;
import com.shellcore.android.shellforkrecipes.recipelist.RecipeListActivity;
import com.shellcore.android.shellforkrecipes.recipemain.RecipeMainPresenter;
import com.shellcore.android.shellforkrecipes.recipemain.di.RecipeMainComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeMainActivity extends AppCompatActivity implements RecipeMainView, SwipeGestureListener {

    // Variables
    private Recipe currentRecipe;

    // Services
    private RecipeMainPresenter presenter;
    private ImageLoader imageLoader;
    private RecipeMainComponent component;

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
        setupImageLoader();
        setupGestureDetection();

        presenter.onCreate();
        presenter.getNextRecipe();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipe_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list:
                navigateToListScreen();
                break;
            case R.id.action_logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnkeep)
    @Override
    public void onKeep() {
        if (currentRecipe != null) {
            presenter.saveRecipe(currentRecipe);
        }
    }

    @OnClick(R.id.btnDismiss)
    @Override
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
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.save_animation);
        anim.setAnimationListener(getAnimationListener());
        imgRecipe.startAnimation(anim);
    }

    @Override
    public void dismissAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dismiss_animation);
        anim.setAnimationListener(getAnimationListener());
        imgRecipe.startAnimation(anim);
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

    private void setupInjection() {
        ShellForkRecipesApplication app = (ShellForkRecipesApplication) getApplication();
        component = app.getRecipeMainComponent(this, this);

        imageLoader = getImageLoader();
        presenter = getPresenter();
    }

    private void setupImageLoader() {
        RequestListener glideRequestListener = new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                presenter.imageError(e.getLocalizedMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                presenter.imageReady();
                return false;
            }
        };
        imageLoader.setOnFinishedImageLoaderListener(glideRequestListener);
    }

    private void setupGestureDetection() {
        final GestureDetector gestureDetector = new GestureDetector(this, new SwipeGestureDetector(this));
        View.OnTouchListener gestureOnTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };

        imgRecipe.setOnTouchListener(gestureOnTouchListener);
    }

    private void navigateToListScreen() {
        startActivity(new Intent(this, RecipeListActivity.class));
    }

    private void logout() {
        ShellForkRecipesApplication app = (ShellForkRecipesApplication) getApplication();
        app.logout();
    }

    private ImageLoader getImageLoader() {
        return component.getImageLoader();
    }

    private RecipeMainPresenter getPresenter() {
        return component.getPresenter();
    }

    private void clearImage() {
        imgRecipe.setImageResource(0);
    }

    private Animation.AnimationListener getAnimationListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                clearImage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }
}
