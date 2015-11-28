package com.shkvarochki.mobilechallenge.ui.screens;

import android.graphics.Camera;
import android.support.v7.app.AppCompatActivity;

import com.shkvarochki.mobilechallenge.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_camera)
public class CameraActivity extends AppCompatActivity {
    private Camera camera;

    @AfterViews
    protected void initViews() {

    }
}
