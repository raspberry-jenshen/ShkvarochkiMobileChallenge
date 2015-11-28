package com.shkvarochki.mobilechallenge.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shkvarochki.mobilechallenge.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.List;

public class FilterImageAdapter extends RecyclerView.Adapter<FilterImageAdapter.ViewHolder> {

    private final Context context;

    private final List<Transformation> transformationList;
    private final String photoUri;

    public FilterImageAdapter(Context context, List<Transformation> transformationList, String photoUri) {
        this.context = context;
        this.transformationList = transformationList;
        this.photoUri = photoUri;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_filter_photo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transformation transformation = transformationList.get(position);

        Picasso.with(context).load(new File(photoUri)).fit().centerCrop().transform(transformation).into(holder.photo_imageView);
    }

    @Override
    public int getItemCount() {
        return transformationList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo_imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            photo_imageView = (ImageView) itemView.findViewById(R.id.photo_imageView);
        }
    }
}
