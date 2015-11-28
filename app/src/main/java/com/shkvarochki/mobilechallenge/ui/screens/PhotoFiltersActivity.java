package com.shkvarochki.mobilechallenge.ui.screens;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.BaseActivity;
import com.shkvarochki.mobilechallenge.ui.adapters.FilterImageAdapter;
import com.shkvarochki.mobilechallenge.ui.screens.entity.TransformationEntity;
import com.shkvarochki.mobilechallenge.utils.BitmapUtils;
import com.shkvarochki.mobilechallenge.utils.ProgressDialogHelper;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@EActivity(R.layout.activity_photo_filter)
public class PhotoFiltersActivity extends BaseActivity implements FilterImageAdapter.OnItemClickListener {
    @Extra
    protected String imageUri;
    @ViewById
    protected Toolbar toolbar;
    @ViewById
    protected RecyclerView recyclerView;
    @Bean
    protected ProgressDialogHelper progressDialogHelper;
    private Subscription subscription;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressDialogHelper.dismissDialog();
    }

    @AfterViews
    protected void afterViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (actionBar != null) {
            actionBar.setTitle(R.string.gallery_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

        FilterImageAdapter adapter = new FilterImageAdapter(getContext(), this, imageUri);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(ImageView imageView, TransformationEntity transformationEntity) {
        progressDialogHelper.showProgressDialog();
        Observable<String> observable = Observable.just(imageUri);
        Action1<Bitmap> onAction = (bitmap) -> {
            onBitmapLoaded(bitmap);
            progressDialogHelper.dismissDialog();
            subscription.unsubscribe();
        };
        Action1<Throwable> onError = throwable -> {
            progressDialogHelper.dismissDialog();
            Toast.makeText(getContext(), "Something went wrong...", Toast.LENGTH_LONG).show();
            subscription.unsubscribe();
        };
        subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(url -> {
                    try {
                        return Picasso.with(this).load(imageUri).transform(transformationEntity.getTransformation()).get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    throw new RuntimeException("Opps");
                })
                .subscribe(onAction, onError);
    }

    public void onBitmapLoaded(Object bitmap) {
        Uri uri = BitmapUtils.getImageUri(getContext(), (Bitmap) bitmap);
        ShareActivity_.intent(this).imageUri(uri).start();
    }
}
