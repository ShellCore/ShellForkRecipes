package com.shellcore.android.shellforkrecipes.recipemain;

import com.shellcore.android.shellforkrecipes.db.entities.Recipe;
import com.shellcore.android.shellforkrecipes.libs.base.EventBus;
import com.shellcore.android.shellforkrecipes.recipemain.events.RecipeMainEvent;
import com.shellcore.android.shellforkrecipes.recipemain.ui.RecipeMainView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 19/07/2017.
 */

public class RecipeMainPresenterImpl implements RecipeMainPresenter {

    private EventBus eventBus;
    private RecipeMainView view;
    private SaveRecipeInteractor saveInteractor;
    private GetNextRecipeInteractor getNextInteractor;

    public RecipeMainPresenterImpl(EventBus eventBus, RecipeMainView view, SaveRecipeInteractor saveInteractor, GetNextRecipeInteractor getNextInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.saveInteractor = saveInteractor;
        this.getNextInteractor = getNextInteractor;
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
    public void dismissRecipe() {
        if (view != null) {
            view.dismissAnimation();
        }
        getNextRecipe();
    }

    @Override
    public void getNextRecipe() {
        if (view != null) {
            view.showProgressbar();
            view.hideUIElements();
        }
        getNextInteractor.execute();
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        if (view != null) {
            view.saveAnimation();
            view.hideUIElements();
            view.showProgressbar();
        }
        saveInteractor.execute(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeMainEvent event) {
        if (view != null) {
            String error = event.getError();
            if (error != null) {
                view.hideProgressbar();
                view.onGetRecipeError(error);
            } else {
                switch (event.getType()) {
                    case RecipeMainEvent.NEXT_EVENT:
                        view.setRecipe(event.getRecipe());
                        break;
                    case RecipeMainEvent.SAVE_EVENT:
                        view.onRecipeSaved();
                        getNextInteractor.execute();
                        break;
                }
            }
        }
    }

    @Override
    public void imageReady() {
        if (view != null) {
            view.hideProgressbar();
            view.showUIElements();
        }
    }

    @Override
    public void imageError(String error) {
        if (view != null) {
            view.onGetRecipeError(error);
        }
    }

    @Override
    public RecipeMainView getView() {
        return view;
    }
}
