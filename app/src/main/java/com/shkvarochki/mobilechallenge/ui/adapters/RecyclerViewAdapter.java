package com.shkvarochki.mobilechallenge.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shkvarochki.mobilechallenge.R;
import com.shkvarochki.mobilechallenge.data.entities.PhotoItem;
import com.shkvarochki.mobilechallenge.ui.models.ViewHolderBase;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolderBase> {

    private final Context context;

    public List<PhotoItem> items = new ArrayList<>();

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<PhotoItem> items) {
        if (items == null) {
            this.items.clear();
            return;
        }
        this.items = items;
    }

    @Override
    public ViewHolderBase onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photo, viewGroup, false);
        return new ViewHolderBase(context, v);
    }

    @Override
    public void onBindViewHolder(ViewHolderBase viewHolder, int i) {
        PhotoItem photoItem = items.get(i);
        viewHolder.setInfo(photoItem);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public List<PhotoItem> getItems() {
        return items;
    }
}
