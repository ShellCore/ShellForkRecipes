package com.shellcore.android.shellforkrecipes.recipemain.di;

import com.shellcore.android.shellforkrecipes.api.RecipeClient;
import com.shellcore.android.shellforkrecipes.api.RecipeService;
import com.shellcore.android.shellforkrecipes.libs.base.EventBus;
import com.shellcore.android.shellforkrecipes.recipemain.GetNextRecipeInteractor;
import com.shellcore.android.shellforkrecipes.recipemain.GetNextRecipeInteractorImpl;
import com.shellcore.android.shellforkrecipes.recipemain.RecipeMainPresenter;
import com.shellcore.android.shellforkrecipes.recipemain.RecipeMainPresenterImpl;
import com.shellcore.android.shellforkrecipes.recipemain.RecipeMainRepository;
import com.shellcore.android.shellforkrecipes.recipemain.RecipeMainRepositoryImpl;
import com.shellcore.android.shellforkrecipes.recipemain.SaveRecipeInteractor;
import com.shellcore.android.shellforkrecipes.recipemain.SaveRecipeInteractorImpl;
import com.shellcore.android.shellforkrecipes.recipemain.ui.RecipeMainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cesar on 19/07/2017.
 */

@Module
public class RecipeMainModule {
    private RecipeMainView view;

    public RecipeMainModule(RecipeMainView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    RecipeMainView providesRecipeMainView() {
        return view;
    }

    @Provides
    @Singleton
    RecipeMainPresenter providesRecipeMainPresenter(EventBus eventBus, RecipeMainView view, SaveRecipeInteractor saveInteractor, GetNextRecipeInteractor getNextInteractor) {
        return new RecipeMainPresenterImpl(eventBus, view, saveInteractor, getNextInteractor);
    }

    @Provides
    @Singleton
    SaveRecipeInteractor providesSaveRecipeInteractor(RecipeMainRepository repository) {
        return new SaveRecipeInteractorImpl(repository);
    }

    @Provides
    @Singleton
    GetNextRecipeInteractor providesGetNextRecipeInteractor(RecipeMainRepository repository) {
        return new GetNextRecipeInteractorImpl(repository);
    }

    @Provides
    @Singleton
    RecipeMainRepository providesRecipeMainRepository(EventBus eventBus, RecipeService service) {
        return new RecipeMainRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    RecipeService providesRecipeService() {
        return new RecipeClient().getRecipeService();
    }
}
