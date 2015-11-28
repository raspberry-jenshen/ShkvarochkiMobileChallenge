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
    private final OnItemClickListener onItemClickListener;

    public FilterImageAdapter(Context context, OnItemClickListener onItemClickListener, String photoUri, List<Transformation> transformationList) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
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
        holder.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(transformation);
            }
        });
        Picasso.with(context).load(new File(photoUri)).fit().centerCrop().transform(transformation).into(holder.photo_imageView);
    }

    @Override
    public int getItemCount() {
        return transformationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView photo_imageView;
        private View.OnClickListener onClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            photo_imageView = (ImageView) itemView.findViewById(R.id.photo_imageView);
            itemView.setOnClickListener(this);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }

    public interface OnItemClickListener {
        void onClick(Transformation transformation);
    }
}
