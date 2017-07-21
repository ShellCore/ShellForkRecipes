package com.shellcore.android.shellforkrecipes.recipelist;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;

/**
 * Created by Cesar on 20/07/2017.
 */

public class StoredRecipesInteractorImpl implements StoredRecipesInteractor {

    private RecipeListRepository repository;

    public StoredRecipesInteractorImpl(RecipeListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeUpdate(Recipe recipe) {
        repository.updateRecipe(recipe);
    }

    @Override
    public void executeDelete(Recipe recipe) {
        repository.removeRecipe(recipe);
    }
}
