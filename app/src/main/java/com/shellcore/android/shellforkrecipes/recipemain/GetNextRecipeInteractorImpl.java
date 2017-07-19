package com.shellcore.android.shellforkrecipes.recipemain;

import java.util.Random;

/**
 * Created by Cesar on 19/07/2017.
 */

public class GetNextRecipeInteractorImpl implements GetNextRecipeInteractor {

    private RecipeMainRepository repository;

    public GetNextRecipeInteractorImpl(RecipeMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        int recipePage = new Random().nextInt(RecipeMainRepository.RECIPE_RANGE);
        repository.setRecipePage(recipePage);
        repository.getNextRecipe();
    }
}
