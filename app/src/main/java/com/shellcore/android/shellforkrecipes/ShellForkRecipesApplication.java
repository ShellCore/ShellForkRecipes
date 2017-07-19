package com.shellcore.android.shellforkrecipes;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Cesar on 19/07/2017.
 */

public class ShellForkRecipesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        setupFacebook();
        setupDatabase();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void setupFacebook() {
        FacebookSdk.sdkInitialize(this);
    }

    private void setupDatabase() {
        FlowManager.init(this);
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }
}
