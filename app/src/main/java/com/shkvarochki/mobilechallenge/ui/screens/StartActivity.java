package com.shkvarochki.mobilechallenge.ui.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.BaseActivity;
import com.shkvarochki.mobilechallenge.ui.screens.gallery.GalleryActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Евгений on 28.11.2015.
 */
@EActivity(R.layout.activity_start)
public class StartActivity extends BaseActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String TAG = "StartActivity";
    @InstanceState
    protected Uri outputFileUri;

    @Click(R.id.openCamera_button)
    protected void onOpenCamera_click() {
        getPhotoFromCamera();
    }

    @Click(R.id.chooseFromGallery_button)
    protected void onOpenGallery_click() {
        GalleryActivity_.intent(this).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            EditPhotoActivity_.intent(this).imageUri(outputFileUri.toString()).start();
        }
    }


    /* private methods */

    private void getPhotoFromCamera() {
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Avatar/";
        File newDir = new File(dir);
        try {
            if (newDir.mkdirs()) {
                Log.d(TAG, "Directories was created: " + newDir.toString());

                String file = dir + DateFormat.format("yyyy-MM-dd_hhmmss", new Date()).toString() + ".jpg";

                File newFile = new File(file);
                if (newFile.createNewFile()) {
                    Log.d(TAG, "File was created: " + newDir.toString());
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    outputFileUri = Uri.fromFile(newFile);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        } catch (IOException e) {
            Toast.makeText(StartActivity.this.getContext(), "Can't create image from camera... ", Toast.LENGTH_SHORT).show();
        }
    }
}
