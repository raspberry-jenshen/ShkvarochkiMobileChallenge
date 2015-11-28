package com.shkvarochki.mobilechallenge.ui.screens;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.BaseActivity;
import com.shkvarochki.mobilechallenge.ui.adapters.FilterImageAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropSquareTransformation;
import jp.wasabeef.picasso.transformations.gpu.ToonFilterTransformation;

@EActivity(R.layout.activity_edit_photo)
public class EditPhotoActivity extends BaseActivity {

    @Extra
    protected String imageUri;

    @ViewById
    protected RecyclerView recyclerView;

    @ViewById
    protected ImageView imageView;


    @AfterViews
    protected void afterViews() {
        imageUri = "/sdcard/image.jpg";
        Picasso.with(getContext()).load(new File(imageUri)).fit().centerCrop().into(imageView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        List<Transformation> transformationList = new ArrayList<>();
        transformationList.add(new ToonFilterTransformation(getContext()));
        transformationList.add(new ColorFilterTransformation(android.R.color.black));
        transformationList.add(new CropSquareTransformation());

        FilterImageAdapter adapter = new FilterImageAdapter(getContext(), transformationList, imageUri);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
