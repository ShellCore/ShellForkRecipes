package com.shellcore.android.shellforkrecipes.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Cesar on 19/07/2017.
 */

@Database(name = RecipesDatabase.NAME, version = RecipesDatabase.VERSION)
public class RecipesDatabase {

    public static final int VERSION = 1;
    public static final String NAME = "Recipes";


}
