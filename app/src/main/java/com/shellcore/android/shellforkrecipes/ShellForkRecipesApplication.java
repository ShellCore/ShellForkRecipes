package com.shellcore.android.shellforkrecipes;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by Cesar on 19/07/2017.
 */

public class ShellForkRecipesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        setupFacebook();
    }

    private void setupFacebook() {
        FacebookSdk.sdkInitialize(this);
    }
}
