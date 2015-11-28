package com.shkvarochki.mobilechallenge.ui.screens;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.isseiaoki.simplecropview.CropImageView;
import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.BaseActivity;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

import java.io.File;

@EActivity(R.layout.activity_edit_photo)
public class EditPhotoActivity extends BaseActivity {

    @Extra
    protected String imageUri;

    @ViewById
    protected CropImageView cropImageView;

    @ViewById
    protected Toolbar toolbar;

    @AfterViews
    protected void afterViews() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (actionBar != null) {
            actionBar.setTitle(R.string.photo_edit_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        imageUri = "/sdcard/image.jpg";

        Picasso.with(getContext()).load(new File(imageUri)).fit().centerCrop().into(cropImageView);
    }

    @Click(R.id.textView11)
    protected void textView11Clicked() {
        cropImageView.setCropMode(CropImageView.CropMode.RATIO_1_1);
    }

    @Click(R.id.textView43)
    protected void textView43Clicked() {
        cropImageView.setCropMode(CropImageView.CropMode.RATIO_4_3);
    }

    @Click(R.id.textView34)
    protected void textView34Clicked() {
        cropImageView.setCropMode(CropImageView.CropMode.RATIO_3_4);
    }

    @Click(R.id.textView169)
    protected void textView169Clicked() {
        cropImageView.setCropMode(CropImageView.CropMode.RATIO_16_9);
    }

    @Click(R.id.textViewFree)
    protected void textViewFreeClicked() {
        cropImageView.setCropMode(CropImageView.CropMode.RATIO_FREE);
    }

    @InstanceState
    protected float rotated;

    @Click(R.id.rotateLeft)
    protected void rotateLeftClicked() {
        rotated--;
        cropImageView.setRotation(rotated);
    }

    @Click(R.id.rotateRight)
    protected void rotateRightClicked() {
        rotated++;
        cropImageView.setRotation(rotated);
    }
}
