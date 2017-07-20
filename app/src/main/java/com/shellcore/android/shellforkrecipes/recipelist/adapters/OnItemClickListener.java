package com.shellcore.android.shellforkrecipes.recipelist.adapters;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;

/**
 * Created by Cesar on 20/07/2017.
 */

public interface OnItemClickListener {
    void onFavClick(Recipe recipe);
    void onItemClick(Recipe recipe);
    void onDeleteClick(Recipe recipe);
}
