package com.shellcore.android.shellforkrecipes.recipelist;

/**
 * Created by Cesar on 20/07/2017.
 */

public class RecipeListInteractorImpl implements RecipeListInteractor {

    private RecipeListRepository repository;

    public RecipeListInteractorImpl(RecipeListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getSavedRecipes();
    }
}
