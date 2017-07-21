package com.shellcore.android.shellforkrecipes.recipemain;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;

/**
 * Created by Cesar on 19/07/2017.
 */

public interface SaveRecipeInteractor {

    void execute(Recipe recipe);
}
