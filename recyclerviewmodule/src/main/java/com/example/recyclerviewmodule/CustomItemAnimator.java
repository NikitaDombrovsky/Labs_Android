package com.example.recyclerviewmodule;

import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

public class CustomItemAnimator extends DefaultItemAnimator {
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        View view = holder.itemView;
        view.setAlpha(0);
        view.animate().alpha(1).setDuration(500).start();
        return super.animateAdd(holder);
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        View view = holder.itemView;
        view.animate().alpha(0).setDuration(5500).start();
        return super.animateRemove(holder);
    }
}