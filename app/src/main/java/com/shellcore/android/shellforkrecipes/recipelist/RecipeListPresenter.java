package com.shellcore.android.shellforkrecipes.recipelist;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;
import com.shellcore.android.shellforkrecipes.recipelist.events.RecipeListEvent;
import com.shellcore.android.shellforkrecipes.recipelist.ui.RecipeListView;

/**
 * Created by Cesar on 20/07/2017.
 */

public interface RecipeListPresenter {

    void onCreate();
    void onDestroy();

    void getRecipes();
    void removeRecipe(Recipe recipe);
    void toggleFavorite(Recipe recipe);
    void onEventMainThread(RecipeListEvent event);

    RecipeListView getView();
}
