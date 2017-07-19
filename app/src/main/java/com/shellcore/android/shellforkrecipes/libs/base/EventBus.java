package com.shellcore.android.shellforkrecipes.libs.base;

/**
 * Created by Cesar on 19/07/2017.
 */

public interface EventBus {

    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
