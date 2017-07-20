package com.shellcore.android.shellforkrecipes.recipelist.ui;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;

import java.util.List;

/**
 * Created by Cesar on 20/07/2017.
 */

public interface RecipeListView {

    void setRecipes(List<Recipe> data);
    void recipeUpdated();
    void recipeDeleted(Recipe recipe);
}
