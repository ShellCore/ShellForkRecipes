package com.shellcore.android.shellforkrecipes.recipemain;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;

/**
 * Created by Cesar on 19/07/2017.
 */

public interface RecipeMainRepository {

    public static final int COUNT = 1;
    public static final String RECENT_SORT = "r";
    public static final int RECIPE_RANGE = 100000;

    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void setRecipePage(int recipePage);
}
