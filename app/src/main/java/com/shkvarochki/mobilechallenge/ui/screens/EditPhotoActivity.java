package com.shkvarochki.mobilechallenge.ui.screens;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.BaseActivity;
import com.shkvarochki.mobilechallenge.ui.adapters.FilterImageAdapter;
import com.shkvarochki.mobilechallenge.utils.FilterHelper;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.File;

@EActivity(R.layout.activity_edit_photo)
public class EditPhotoActivity extends BaseActivity {

    @Extra
    protected String imageUri;

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected RecyclerView recyclerView;

    @ViewById
    protected ImageView imageView;


    @AfterViews
    protected void afterViews() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (actionBar != null) {
            actionBar.setTitle(R.string.edit_photo_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        imageUri = "/sdcard/image.jpg";
        Picasso.with(getContext()).load(new File(imageUri)).fit().centerCrop().into(imageView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        FilterImageAdapter adapter = new FilterImageAdapter(getContext(), FilterHelper.getSupportedTransformationList(getContext()), imageUri);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
