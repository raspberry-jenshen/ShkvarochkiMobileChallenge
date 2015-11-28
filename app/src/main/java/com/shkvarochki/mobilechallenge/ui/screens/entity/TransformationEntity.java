package com.shkvarochki.mobilechallenge.ui.screens.entity;

import com.squareup.picasso.Transformation;

public class TransformationEntity {

    private Transformation transformation;
    private String name;

    public TransformationEntity(Transformation transformation, String name) {
        this.transformation = transformation;
        this.name = name;
    }

    public Transformation getTransformation() {
        return transformation;
    }

    public String getName() {
        return name;
    }
}
