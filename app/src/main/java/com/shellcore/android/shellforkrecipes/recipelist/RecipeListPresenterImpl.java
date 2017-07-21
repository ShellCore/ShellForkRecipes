package com.shellcore.android.shellforkrecipes.recipelist;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;
import com.shellcore.android.shellforkrecipes.libs.base.EventBus;
import com.shellcore.android.shellforkrecipes.recipelist.events.RecipeListEvent;
import com.shellcore.android.shellforkrecipes.recipelist.ui.RecipeListView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 20/07/2017.
 */

public class RecipeListPresenterImpl implements RecipeListPresenter {

    private EventBus eventBus;
    private RecipeListView view;
    private RecipeListInteractor listInteractor;
    private StoredRecipesInteractor storedInteractor;

    public RecipeListPresenterImpl(EventBus eventBus, RecipeListView view, RecipeListInteractor listInteractor, StoredRecipesInteractor storedInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.listInteractor = listInteractor;
        this.storedInteractor = storedInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void getRecipes() {
        listInteractor.execute();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        storedInteractor.executeDelete(recipe);
    }

    @Override
    public void toggleFavorite(Recipe recipe) {
        boolean fav = recipe.isFavorite();
        recipe.setFavorite(!fav);
        storedInteractor.executeUpdate(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeListEvent event) {
        if (view != null) {
            switch (event.getType()) {
                case RecipeListEvent.READ_EVENT:
                    view.setRecipes(event.getRecipes());
                    break;
                case RecipeListEvent.UPDATE_EVENT:
                    view.recipeUpdated();
                    break;
                case RecipeListEvent.DELETE_EVENT:
                    Recipe recipe = event.getRecipes().get(0);
                    view.recipeDeleted(recipe);
                    break;
            }
        }
    }

    @Override
    public RecipeListView getView() {
        return view;
    }
}
