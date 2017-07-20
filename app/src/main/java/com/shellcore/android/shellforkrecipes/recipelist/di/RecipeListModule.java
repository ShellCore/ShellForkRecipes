package com.shellcore.android.shellforkrecipes.recipelist.di;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;
import com.shellcore.android.shellforkrecipes.libs.base.EventBus;
import com.shellcore.android.shellforkrecipes.libs.base.ImageLoader;
import com.shellcore.android.shellforkrecipes.recipelist.RecipeListInteractor;
import com.shellcore.android.shellforkrecipes.recipelist.RecipeListInteractorImpl;
import com.shellcore.android.shellforkrecipes.recipelist.RecipeListPresenter;
import com.shellcore.android.shellforkrecipes.recipelist.RecipeListPresenterImpl;
import com.shellcore.android.shellforkrecipes.recipelist.RecipeListRepository;
import com.shellcore.android.shellforkrecipes.recipelist.RecipeListRepositoryImpl;
import com.shellcore.android.shellforkrecipes.recipelist.StoredRecipesInteractor;
import com.shellcore.android.shellforkrecipes.recipelist.StoredRecipesInteractorImpl;
import com.shellcore.android.shellforkrecipes.recipelist.adapters.OnItemClickListener;
import com.shellcore.android.shellforkrecipes.recipelist.adapters.RecipeListAdapter;
import com.shellcore.android.shellforkrecipes.recipelist.ui.RecipeListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeListModule {
    private RecipeListView view;
    private OnItemClickListener listener;

    public RecipeListModule(RecipeListView view, OnItemClickListener listener) {
        this.view = view;
        this.listener = listener;
    }

    @Provides
    @Singleton
    RecipeListView providesRecipeListView() {
        return view;
    }

    @Provides
    @Singleton
    RecipeListPresenter providesRecipeListPresenter(EventBus eventBus, RecipeListView view, RecipeListInteractor listInteractor, StoredRecipesInteractor storedInteractor) {
        return new RecipeListPresenterImpl(eventBus, view, listInteractor, storedInteractor);
    }

    @Provides
    @Singleton
    StoredRecipesInteractor providesStoredRecipesInteractor(RecipeListRepository repository) {
        return new StoredRecipesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    RecipeListInteractor providesRecipeListInteractor(RecipeListRepository repository) {
        return new RecipeListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    RecipeListRepository providesRecipeListRepository(EventBus eventBus) {
        return new RecipeListRepositoryImpl(eventBus);
    }

    @Provides
    @Singleton
    RecipeListAdapter providesRecipesAdapter(ImageLoader imageLoader, List<Recipe> recipes, OnItemClickListener onItemClickListener) {
        return new RecipeListAdapter(imageLoader, recipes, onItemClickListener);
    }

    @Provides
    @Singleton
    List<Recipe> providesEmptyList() {
        return new ArrayList<Recipe>();
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return listener;
    }
}
