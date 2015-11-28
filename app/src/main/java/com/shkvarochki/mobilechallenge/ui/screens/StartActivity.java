package com.shkvarochki.mobilechallenge.ui.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.BaseActivity;
import com.shkvarochki.mobilechallenge.ui.screens.gallery.GalleryActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;

import java.io.File;
import java.io.IOException;

@EActivity(R.layout.activity_start)
public class StartActivity extends BaseActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    @InstanceState
    private Uri contentUri;

    @Click(R.id.openCamera_button)
    protected void onOpenCamera_click() {
        try {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            File image = createImageFile();
            contentUri = Uri.fromFile(image);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Can't create file", Toast.LENGTH_LONG).show();
        }
    }

    @Click(R.id.chooseFromGallery_button)
    protected void onOpenGallery_click() {
        GalleryActivity_.intent(this).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            mediaScanIntent.setData(contentUri);
            sendBroadcast(mediaScanIntent);
            EditPhotoActivity_.intent(this).imageUri(contentUri.toString()).isFromCamera(true).start();
        }
    }


    /* private methods */

    private File createImageFile() throws IOException {// Create an image file name
        String appName = getString(R.string.app_name);
        String imageFileName = "Avatar_".concat(appName);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES
                + "/".concat(appName));

        if (storageDir.isDirectory()) {
            Stream.of(storageDir.listFiles()).forEach(File::delete);
        } else {
            storageDir.mkdirs();
        }
        return File.createTempFile(
                imageFileName,
                ".jpeg",
                storageDir
        );
    }
}
