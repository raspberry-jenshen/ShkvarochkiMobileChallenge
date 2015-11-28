package com.shkvarochki.mobilechallenge.ui.screens;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.BaseActivity;
import com.shkvarochki.mobilechallenge.ui.adapters.FilterImageAdapter;
import com.shkvarochki.mobilechallenge.utils.FilterHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_edit_photo)
public class EditPhotoActivity extends BaseActivity {

    @Extra
    protected String imageUri;

    @ViewById
    protected ImageView imageView;

    @ViewById
    protected Toolbar toolbar;

    @AfterViews
    protected void afterViews() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (actionBar != null) {
            actionBar.setTitle(R.string.photo_filters_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
