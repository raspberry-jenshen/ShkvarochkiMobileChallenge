package com.shkvarochki.mobilechallenge.utils.picasso;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

public class NoneTransformation implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        return source;
    }

    @Override
    public String key() {
        return "untransformed";
    }
}
