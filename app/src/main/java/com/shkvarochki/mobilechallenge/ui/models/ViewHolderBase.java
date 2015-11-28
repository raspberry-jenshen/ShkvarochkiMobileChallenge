package com.shkvarochki.mobilechallenge.ui.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shkvarochki.mobilechallenge.data.entities.PhotoItem;

/**
 * Created by Евгений on 28.11.2015.
 */
public class ViewHolderBase extends RecyclerView.ViewHolder {
    private PhotoItem info;

    public ViewHolderBase(Context context, View itemView) {
        super(itemView);
    }

    public void setInfo(PhotoItem info) {
        this.info = info;
    }
}
