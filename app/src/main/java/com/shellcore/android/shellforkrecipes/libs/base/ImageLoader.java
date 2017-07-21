package com.shellcore.android.shellforkrecipes.libs.base;

import android.widget.ImageView;

/**
 * Created by Cesar on 19/07/2017.
 */

public interface ImageLoader {

    void load(ImageView view, String url);
    void setOnFinishedImageLoaderListener(Object listener);
}
