package com.shellcore.android.shellforkrecipes;

import android.app.Application;
import android.content.Intent;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.shellcore.android.shellforkrecipes.login.ui.LoginActivity;

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

    public void logout() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
