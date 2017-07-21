package com.shellcore.android.shellforkrecipes.recipemain;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;

/**
 * Created by Cesar on 19/07/2017.
 */

public class SaveRecipeInteractorImpl implements SaveRecipeInteractor {

    private RecipeMainRepository repository;

    public SaveRecipeInteractorImpl(RecipeMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Recipe recipe) {
        repository.saveRecipe(recipe);
    }
}
