package com.shkvarochki.mobilechallenge.ui.screens.gallery;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.BaseActivity;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_share)
@OptionsMenu(R.menu.menu_share)
public class ShareActivity extends BaseActivity {

    @Extra
    protected String imageUri;

    @ViewById
    ImageView readyPhoto_imageView;

    @ViewById
    protected Toolbar toolbar;

    @AfterViews
    protected void afterViews() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.share_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }

        Picasso.with(this.getContext()).load(imageUri).fit().centerCrop().into(readyPhoto_imageView);
    }

    @OptionsItem(R.id.action_share)
    protected void shareClicked() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);

        sharingIntent.setType("image/jpeg");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(sharingIntent, "Share image using"));
    }

}
