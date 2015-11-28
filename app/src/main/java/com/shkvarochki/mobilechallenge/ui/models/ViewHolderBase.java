package com.shkvarochki.mobilechallenge.ui.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.annimon.stream.Optional;
import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.data.entities.PhotoItem;
import com.squareup.picasso.Picasso;

/**
 * Created by Евгений on 28.11.2015.
 */
public class ViewHolderBase extends RecyclerView.ViewHolder {
    private Context context;
    private View itemView;

    public ViewHolderBase(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
    }

    public void setInfo(PhotoItem info) {
        ImageView photo_imageView = (ImageView) itemView.findViewById(R.id.photo_imageView);

        Optional<String> uri = info.getUri();
        if (uri.isPresent()) {
            Picasso.with(context)
                    .load(uri.get())
                    .fit()
                    .centerCrop()
                    .into(photo_imageView);
        } else {
            photo_imageView.setImageResource(R.drawable.ic_camera_alt_black_24dp);
        }
    }
}
