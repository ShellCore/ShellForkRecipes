package com.shellcore.android.shellforkrecipes.recipemain.di;

import com.shellcore.android.shellforkrecipes.libs.base.ImageLoader;
import com.shellcore.android.shellforkrecipes.libs.di.LibsModule;
import com.shellcore.android.shellforkrecipes.recipemain.RecipeMainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cesar on 19/07/2017.
 */

@Singleton
@Component(modules = {RecipeMainModule.class, LibsModule.class})
public interface RecipeMainComponent {
    //void inject(RecipeMainActivity activity);
    ImageLoader getImageLoader();
    RecipeMainPresenter getPresenter();
}
