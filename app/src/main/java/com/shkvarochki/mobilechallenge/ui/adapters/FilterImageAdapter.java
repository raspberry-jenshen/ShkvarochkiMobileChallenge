package com.shkvarochki.mobilechallenge.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.ui.screens.entity.TransformationEntity;
import com.shkvarochki.mobilechallenge.utils.picasso.NoneTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.VignetteFilterTransformation;

public class FilterImageAdapter extends RecyclerView.Adapter<FilterImageAdapter.ViewHolder> {

    private final Context context;
    private final List<TransformationEntity> transformationList;
    private final String photoUri;
    private final OnItemClickListener onItemClickListener;

    public FilterImageAdapter(Context context, OnItemClickListener onItemClickListener, String photoUri) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        transformationList = getTransformationList(context);
        this.photoUri = photoUri;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_filter_photo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TransformationEntity transformation = transformationList.get(position);
        holder.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(holder.photo_imageView, transformation);
            }
        });
        Picasso.with(context).load(photoUri).fit().centerCrop().transform(transformation.getTransformation()).into(holder.photo_imageView);
        holder.filterName.setText(transformation.getName());
    }

    @Override
    public int getItemCount() {
        return transformationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView photo_imageView;
        private TextView filterName;
        private View.OnClickListener onClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            photo_imageView = (ImageView) itemView.findViewById(R.id.photo_imageView);
            filterName = (TextView) itemView.findViewById(R.id.filterName);
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
        void onClick(ImageView imageView, TransformationEntity transformationEntity);
    }

    private List<TransformationEntity> getTransformationList(Context context) {
        List<TransformationEntity> transformationList = new ArrayList<>();
        transformationList.add(new TransformationEntity(new NoneTransformation(), context.getString(R.string.filter_none)));
        transformationList.add(new TransformationEntity(new ToonFilterTransformation(context), context.getString(R.string.filter_toon)));
        transformationList.add(new TransformationEntity(new ContrastFilterTransformation(context), context.getString(R.string.filter_contrast)));
        transformationList.add(new TransformationEntity(new InvertFilterTransformation(context), context.getString(R.string.filter_invert)));
        transformationList.add(new TransformationEntity(new PixelationFilterTransformation(context), context.getString(R.string.filter_pixelising)));
        transformationList.add(new TransformationEntity(new SketchFilterTransformation(context), context.getString(R.string.filter_scretch)));
        transformationList.add(new TransformationEntity(new SwirlFilterTransformation(context), context.getString(R.string.filter_swirl)));
        transformationList.add(new TransformationEntity(new BrightnessFilterTransformation(context), context.getString(R.string.filter_brightness)));
        transformationList.add(new TransformationEntity(new VignetteFilterTransformation(context), context.getString(R.string.filter_vignette)));
        return transformationList;
    }
}
