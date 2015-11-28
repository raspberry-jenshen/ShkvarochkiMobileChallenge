package com.shkvarochki.mobilechallenge.utils;

import android.content.Context;

import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.VignetteFilterTransformation;

public class FilterHelper {

    public static List<Transformation> getSupportedTransformationList(Context context) {
        List<Transformation> transformationList = new ArrayList<>();
        transformationList.add(new ToonFilterTransformation(context));
        transformationList.add(new ContrastFilterTransformation(context));
        transformationList.add(new InvertFilterTransformation(context));
        transformationList.add(new PixelationFilterTransformation(context));
        transformationList.add(new SketchFilterTransformation(context));
        transformationList.add(new SwirlFilterTransformation(context));
        transformationList.add(new BrightnessFilterTransformation(context));
        transformationList.add(new KuwaharaFilterTransformation(context));
        transformationList.add(new VignetteFilterTransformation(context));
        return transformationList;
    }

}
