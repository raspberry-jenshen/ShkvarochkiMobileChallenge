package com.shkvarochki.mobilechallenge.ui.screens;


import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
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

import java.io.File;

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
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (actionBar != null) {
            actionBar.setTitle(R.string.share_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }

        Picasso.with(this.getContext()).load(imageUri).fit().centerCrop().into(readyPhoto_imageView);
    }

    @OptionsItem(R.id.action_share)
    protected void shareClicked() {
        Intent share = new Intent(Intent.ACTION_SEND);

        share.setType("image/jpeg");

        File imageFileToShare = new File(imageUri);

        share.putExtra(Intent.EXTRA_STREAM, imageFileToShare);

        startActivity(Intent.createChooser(share, "Share Image!"));
    }

}
