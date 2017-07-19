package com.shellcore.android.shellforkrecipes.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cesar on 19/07/2017.
 */

public class RecipeClient {

    private static final String BASE_URL = "http://food2fork.com/api/";

    private Retrofit retrofit;

    public RecipeClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RecipeService getRecipeService() {
        return retrofit.create(RecipeService.class);
    }
}
