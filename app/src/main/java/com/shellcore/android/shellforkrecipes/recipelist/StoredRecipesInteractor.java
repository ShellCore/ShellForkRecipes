package com.shellcore.android.shellforkrecipes.recipelist;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;

/**
 * Created by Cesar on 20/07/2017.
 */

public interface StoredRecipesInteractor {

    void executeUpdate(Recipe recipe);
    void executeDelete(Recipe recipe);
}
