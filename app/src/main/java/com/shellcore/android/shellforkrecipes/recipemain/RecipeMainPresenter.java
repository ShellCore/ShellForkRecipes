package com.shellcore.android.shellforkrecipes.recipemain;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;
import com.shellcore.android.shellforkrecipes.recipemain.events.RecipeMainEvent;
import com.shellcore.android.shellforkrecipes.recipemain.ui.RecipeMainView;

/**
 * Created by Cesar on 19/07/2017.
 */

public interface RecipeMainPresenter {

    void onCreate();
    void onDestroy();

    void dismissRecipe();
    void getNextRecipe();
    void saveRecipe(Recipe recipe);

    void onEventMainThread(RecipeMainEvent event);

    void imageReady();
    void imageError(String error);
    RecipeMainView getView(); // This is util when we require to test the code
}
