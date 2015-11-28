package com.shkvarochki.mobilechallenge.ui.screens.gallery;

import android.content.Context;
import android.net.Uri;

import com.shkvarochki.mobilechallenge.data.entities.PhotoItem;

/**
 * Created by Евгений on 28.11.2015.
 */
public interface IGalleryPresenter {
    void initLoaders();

    String getPath(Context context, Uri uri);
}
