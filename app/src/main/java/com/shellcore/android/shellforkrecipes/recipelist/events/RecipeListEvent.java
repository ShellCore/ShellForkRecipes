package com.shellcore.android.shellforkrecipes.recipelist.events;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;

import java.util.List;

/**
 * Created by Cesar on 20/07/2017.
 */

public class RecipeListEvent {

    public static final int READ_EVENT = 0;
    public static final int UPDATE_EVENT = 1;
    public static final int DELETE_EVENT = 2;

    private int type;
    private List<Recipe> recipes;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
