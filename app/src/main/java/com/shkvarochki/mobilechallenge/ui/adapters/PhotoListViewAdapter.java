package com.shkvarochki.mobilechallenge.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PhotoListViewAdapter extends RecyclerView.Adapter<PhotoListViewAdapter.PhotoHolder> {

    public static final int Photo_Item = 0;
    public static final int AddPhotoFromCamera_Item = 1;

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class PhotoHolder extends RecyclerView.ViewHolder {

        private ImageView photo_imageView;
        private ImageView selector_imageView;

        public PhotoHolder(View itemView) {
            super(itemView);
        }
    }
}
