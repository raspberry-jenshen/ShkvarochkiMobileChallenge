package com.shkvarochki.mobilechallenge.ui.screens;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.BaseActivity;
import com.shkvarochki.mobilechallenge.ui.screens.gallery.GalleryActivity_;
import com.shkvarochki.mobilechallenge.utils.BitmapUtils;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_start)
public class StartActivity extends BaseActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Click(R.id.openCamera_button)
    protected void onOpenCamera_click() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Click(R.id.chooseFromGallery_button)
    protected void onOpenGallery_click() {
        GalleryActivity_.intent(this).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Uri uri = BitmapUtils.getImageUri(getContext(), imageBitmap);
            EditPhotoActivity_.intent(this).imageUri(uri.toString()).start();
        }
    }
}
