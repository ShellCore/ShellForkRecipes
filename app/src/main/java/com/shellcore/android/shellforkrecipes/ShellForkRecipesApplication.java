package com.shellcore.android.shellforkrecipes;

import android.app.Application;
import android.content.Intent;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.shellcore.android.shellforkrecipes.libs.di.LibsModule;
import com.shellcore.android.shellforkrecipes.login.ui.LoginActivity;
import com.shellcore.android.shellforkrecipes.recipelist.RecipeListActivity;
import com.shellcore.android.shellforkrecipes.recipelist.adapters.OnItemClickListener;
import com.shellcore.android.shellforkrecipes.recipelist.di.DaggerRecipeListComponent;
import com.shellcore.android.shellforkrecipes.recipelist.di.RecipeListComponent;
import com.shellcore.android.shellforkrecipes.recipelist.di.RecipeListModule;
import com.shellcore.android.shellforkrecipes.recipelist.ui.RecipeListView;
import com.shellcore.android.shellforkrecipes.recipemain.di.DaggerRecipeMainComponent;
import com.shellcore.android.shellforkrecipes.recipemain.di.RecipeMainComponent;
import com.shellcore.android.shellforkrecipes.recipemain.di.RecipeMainModule;
import com.shellcore.android.shellforkrecipes.recipemain.ui.RecipeMainActivity;
import com.shellcore.android.shellforkrecipes.recipemain.ui.RecipeMainView;

/**
 * Created by Cesar on 19/07/2017.
 */

public class ShellForkRecipesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        setupFacebook();
        setupDatabase();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void setupFacebook() {
        FacebookSdk.sdkInitialize(this);
    }

    private void setupDatabase() {
        FlowManager.init(this);
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public RecipeMainComponent getRecipeMainComponent(RecipeMainActivity activity, RecipeMainView view) {
        return DaggerRecipeMainComponent.builder()
                .libsModule(new LibsModule(activity))
                .recipeMainModule(new RecipeMainModule(view))
                .build();
    }

    public RecipeListComponent getRecipeListComponent(RecipeListActivity activity, RecipeListView view, OnItemClickListener clickListener) {
        return DaggerRecipeListComponent.builder()
                .libsModule(new LibsModule(activity))
                .recipeListModule(new RecipeListModule(view, clickListener))
                .build();
    }
}
