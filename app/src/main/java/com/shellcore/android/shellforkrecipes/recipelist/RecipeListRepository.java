package com.shellcore.android.shellforkrecipes.recipelist;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;

/**
 * Created by Cesar on 20/07/2017.
 */

public interface RecipeListRepository {

    void getSavedRecipes();
    void updateRecipe(Recipe recipe);
    void removeRecipe(Recipe recipe);
}
