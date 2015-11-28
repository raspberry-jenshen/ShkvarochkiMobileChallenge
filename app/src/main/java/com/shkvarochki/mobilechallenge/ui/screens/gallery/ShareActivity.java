package com.shkvarochki.mobilechallenge.ui.screens.gallery;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.BaseActivity;
import com.shkvarochki.mobilechallenge.ui.screens.PhotoFiltersActivity_;
import com.shkvarochki.mobilechallenge.utils.BitmapUtils;


import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

@EActivity(R.layout.activity_share)
@OptionsMenu(R.menu.menu_share)
public class ShareActivity extends BaseActivity {

    @Extra
    protected String imageUri;

    @OptionsItem(R.id.action_share)
    protected void shareClicked() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);

        sharingIntent.setType("image/jpeg");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(sharingIntent, "Share image using"));
    }

}
