package com.shellcore.android.shellforkrecipes.recipemain.ui;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;

/**
 * Created by Cesar on 19/07/2017.
 */

public interface RecipeMainView {

    void showProgressbar();
    void hideProgressbar();
    void showUIElements();
    void hideUIElements();
    void saveAnimation();
    void dismissAnimation();

    void onRecipeSaved();

    void setRecipe(Recipe recipe);
    void onGetRecipeError(String error);
}
