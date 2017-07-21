package com.shellcore.android.shellforkrecipes.recipemain;

import com.shellcore.android.shellforkrecipes.BuildConfig;
import com.shellcore.android.shellforkrecipes.api.RecipeSearchResponse;
import com.shellcore.android.shellforkrecipes.api.RecipeService;
import com.shellcore.android.shellforkrecipes.db.entities.Recipe;
import com.shellcore.android.shellforkrecipes.libs.base.EventBus;
import com.shellcore.android.shellforkrecipes.recipemain.events.RecipeMainEvent;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cesar on 19/07/2017.
 */

public class RecipeMainRepositoryImpl implements RecipeMainRepository {

    private int recipePage;
    private EventBus eventBus;
    private RecipeService service;

    public RecipeMainRepositoryImpl(EventBus eventBus, RecipeService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void getNextRecipe() {
        Call<RecipeSearchResponse> call = service.search(BuildConfig.FOOD_API_KEY,
                RECENT_SORT, COUNT, recipePage);
        call.enqueue(new Callback<RecipeSearchResponse>() {
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                if (response.isSuccessful()) {
                    RecipeSearchResponse recipeSearchResponse = response.body();
                    if (recipeSearchResponse.getCount() == 0) {
                        // Responds, but the service don't come with recipe
                        setRecipePage(new Random().nextInt(RECIPE_RANGE));
                        getNextRecipe();
                    } else{
                        Recipe recipe = recipeSearchResponse.getFirstRecipe();
                        if (recipe != null) {
                            post(recipe);
                        } else {
                            post(response.message());
                        }
                    }
                } else {
                    post(response.message()); // Responds with error
                }
            }

            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
                post(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        recipe.save();
        post();
    }

    @Override
    public void setRecipePage(int recipePage) {
        this.recipePage = recipePage;
    }

    private void post(Recipe recipe, int type, String error) {
        RecipeMainEvent event = new RecipeMainEvent();
        event.setRecipe(recipe);
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }

    private void post(Recipe recipe) {
        post(recipe, RecipeMainEvent.NEXT_EVENT, null);
    }

    private void post(String error) {
        post(null, RecipeMainEvent.NEXT_EVENT, error);
    }

    private void post() {
        post(null, RecipeMainEvent.SAVE_EVENT, null);
    }
}
