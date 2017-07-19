package com.shellcore.android.shellforkrecipes.recipemain.events;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;

/**
 * Created by Cesar on 19/07/2017.
 */

public class RecipeMainEvent {

    public static final int NEXT_EVENT = 1;
    public static final int SAVE_EVENT = 2;

    private int type;
    private Recipe recipe;
    private String error;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
