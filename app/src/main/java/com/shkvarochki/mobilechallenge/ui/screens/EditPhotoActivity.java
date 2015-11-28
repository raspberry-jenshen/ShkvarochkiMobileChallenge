package com.shkvarochki.mobilechallenge.ui.screens;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.isseiaoki.simplecropview.CropImageView;
import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.BaseActivity;
import com.shkvarochki.mobilechallenge.utils.BitmapUtils;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_edit_photo)
@OptionsMenu(R.menu.menu_forward)
public class EditPhotoActivity extends BaseActivity {

    @Extra
    protected String imageUri;

    @Extra
    protected boolean isFromCamera;

    @ViewById
    protected CropImageView cropImageView;

    @ViewById
    protected Toolbar toolbar;
    @InstanceState
    protected float rotated;

    @AfterViews
    protected void afterViews() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (actionBar != null) {
            actionBar.setTitle(R.string.photo_edit_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }

        if (isFromCamera) {
            String imagePath = "file://".concat(imageUri);
            Picasso.with(this).load(imagePath).fit().centerInside().into(cropImageView);
        } else {
            Picasso.with(this).load(imageUri).fit().centerInside().into(cropImageView);
        }
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

    @Click(R.id.rotateLeft)
    protected void rotateLeftClicked() {
        cropImageView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
    }

    @OptionsItem(R.id.action_forward)
    protected void forwardClicked() {
        Bitmap bitmap = cropImageView.getCroppedBitmap();
        Uri uri = BitmapUtils.getImageUri(getContext(), bitmap);

        PhotoFiltersActivity_.intent(getContext()).imageUri(uri.toString()).start();
    }
}
