package com.shkvarochki.mobilechallenge.ui.screens.gallery;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;

/**
 * Created by Евгений on 28.11.2015.
 */
public interface IGalleryView {
    Context getContext();

    void setData(Cursor data);

    void onLoaderReset();

    LoaderManager getSupportLoaderManager();
}
