package com.shkvarochki.mobilechallenge.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Stream;
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

    public void setData(List<PhotoItem> games) {
        if (games == null) {
            items.clear();
            return;
        }
        Stream.of(games).filter(item -> !items.contains(item)).forEach(this::addItem);
        Stream.of(items).filter(item -> !games.contains(item)).forEach(this::deleteItem);
    }

    private void addItem(PhotoItem tableInfo) {
        if (items.size() > 0) {
            items.add(0, tableInfo);
            notifyItemInserted(0);
        } else {
            items.add(tableInfo);
            notifyDataSetChanged();
        }
    }

    private void deleteItem(PhotoItem tableInfo) {
        int position = items.indexOf(tableInfo);
        items.remove(tableInfo);
        notifyItemRemoved(position);
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
}
