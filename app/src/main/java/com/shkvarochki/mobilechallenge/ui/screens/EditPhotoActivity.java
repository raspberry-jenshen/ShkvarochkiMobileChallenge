package com.shkvarochki.mobilechallenge.ui.screens;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.BaseActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_edit_photo)
public class EditPhotoActivity extends BaseActivity {

    @ViewById
    protected ImageView imageView;

    @ViewById
    protected Toolbar toolbar;
}
