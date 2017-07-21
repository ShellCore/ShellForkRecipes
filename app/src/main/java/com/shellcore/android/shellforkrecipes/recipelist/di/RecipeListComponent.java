package com.shellcore.android.shellforkrecipes.recipelist.di;

import com.shellcore.android.shellforkrecipes.libs.di.LibsModule;
import com.shellcore.android.shellforkrecipes.recipelist.RecipeListPresenter;
import com.shellcore.android.shellforkrecipes.recipelist.adapters.RecipeListAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RecipeListModule.class, LibsModule.class})
public interface RecipeListComponent {
    RecipeListAdapter getAdapter();
    RecipeListPresenter getPresenter();
}
