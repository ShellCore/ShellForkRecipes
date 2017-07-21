package com.shellcore.android.shellforkrecipes.recipelist;

import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.shellcore.android.shellforkrecipes.db.entities.Recipe;
import com.shellcore.android.shellforkrecipes.libs.base.EventBus;
import com.shellcore.android.shellforkrecipes.recipelist.events.RecipeListEvent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Cesar on 20/07/2017.
 */

public class RecipeListRepositoryImpl implements RecipeListRepository {

    private EventBus eventBus;

    public RecipeListRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedRecipes() {
        FlowCursorList<Recipe> storedRecipes = new FlowCursorList.Builder<Recipe>(Recipe.class)
                .cacheModels(true)
                .build();
        post(RecipeListEvent.READ_EVENT, storedRecipes.getAll());
        storedRecipes.close();
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipe.update();
        post();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        recipe.delete();
        post(RecipeListEvent.DELETE_EVENT, Arrays.asList(recipe));
    }

    private void post(int type, List<Recipe> recipes) {
        RecipeListEvent event = new RecipeListEvent();
        event.setType(type);
        event.setRecipes(recipes);
        eventBus.post(event);
    }

    private void post() {
        post(RecipeListEvent.UPDATE_EVENT, null);
    }
}
