package com.shkvarochki.mobilechallenge.ui.screens;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.BaseActivity;
import com.shkvarochki.mobilechallenge.ui.adapters.FilterImageAdapter;
import com.shkvarochki.mobilechallenge.ui.screens.gallery.ShareActivity_;
import com.shkvarochki.mobilechallenge.utils.BitmapUtils;
import com.shkvarochki.mobilechallenge.utils.FilterHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_photo_filter)
public class PhotoFiltersActivity extends BaseActivity implements FilterImageAdapter.OnItemClickListener {

    @Extra
    protected String imageUri;

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected RecyclerView recyclerView;

    @AfterViews
    protected void afterViews() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (actionBar != null) {
            actionBar.setTitle(R.string.photo_filters_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

        FilterImageAdapter adapter = new FilterImageAdapter(getContext(), this, imageUri, FilterHelper.getSupportedTransformationList(getContext()));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Uri uri = BitmapUtils.getImageUri(getContext(), bitmap);
        ShareActivity_.intent(this).imageUri(uri.toString()).start();
    }
}
