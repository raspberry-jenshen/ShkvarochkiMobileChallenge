package com.shkvarochki.mobilechallenge.ui.screens.gallery;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.data.entities.PhotoItem;
import com.shkvarochki.mobilechallenge.listeners.RecyclerClickListener;
import com.shkvarochki.mobilechallenge.ui.UiStateController;
import com.shkvarochki.mobilechallenge.ui.adapters.RecyclerViewAdapter;
import com.shkvarochki.mobilechallenge.ui.screens.EditPhotoActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_gallery)
public class GalleryActivity extends AppCompatActivity implements IGalleryView {

    private final IGalleryPresenter presenter;

    @ViewById
    protected RecyclerView recyclerView;
    @ViewById
    protected View loading_ui_View;
    @ViewById
    protected View error_ui_View;
    @ViewById
    protected View empty_ui_View;
    private RecyclerViewAdapter recyclerViewAdapter;
    private UiStateController uiStateController;

    public GalleryActivity() {
        presenter = new GalleryPresenter(this);
    }

    @AfterViews
    protected void afterViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LinearLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.addOnItemTouchListener(new RecyclerClickListener(this) {
            @Override
            public void onItemClick(RecyclerView recyclerView, View itemView, int position) {

            }
        });

        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext());
        recyclerView.setAdapter(recyclerViewAdapter);
        uiStateController = new UiStateController.Builder()
                .withLoadingUi(loading_ui_View)
                .withErrorUi(error_ui_View)
                .withEmptyUi(empty_ui_View)
                .withContentUi(recyclerView)
                .build();
        loadData();
    }

    private void loadData() {
        presenter.initLoaders();
        uiStateController.setUiStateLoading();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setData(Cursor data) {
        int idColumnIndex = data.getColumnIndex(MediaStore.Images.ImageColumns._ID);
        List<PhotoItem> photoList = new ArrayList<>();
        PhotoItem photoItemUrl;
        while (data.moveToNext()) {
            int id = data.getInt(idColumnIndex);
            Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(id));
            photoItemUrl = new PhotoItem(uri.toString());
            photoList.add(photoItemUrl);
        }

        if (photoList.size() == 1) {
            uiStateController.setUiStateEmpty();
            recyclerViewAdapter.setData(null);
        } else {
            uiStateController.setUiStateContent();
            recyclerViewAdapter.setData(photoList);
        }
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset() {
        uiStateController.setUiStateError();
        recyclerViewAdapter.setData(null);
    }

}
