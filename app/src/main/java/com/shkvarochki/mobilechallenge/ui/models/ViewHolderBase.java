package com.shkvarochki.mobilechallenge.ui.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.data.entities.PhotoItem;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Евгений on 28.11.2015.
 */
public class ViewHolderBase extends RecyclerView.ViewHolder {

    private PhotoItem info;
    private Context context;
    private View itemView;

    public ViewHolderBase(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
    }

    public void setInfo(PhotoItem info) {
        this.info = info;
        ImageView photo_imageView = (ImageView) itemView.findViewById(R.id.photo_imageView);
        Picasso.with(context)
                .load(info.getUri())
                .fit()
                .centerCrop()
                .into(photo_imageView);
    }
}
