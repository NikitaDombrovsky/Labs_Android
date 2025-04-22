package com.example.supabasejavaokhttp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.supabasejavaokhttp.Model.Orders;

import java.util.List;

public class OrdersAdapter  extends RecyclerView.Adapter<OrdersAdapter.ItemViewHolder> {
    private List<Orders> items;
    private Context context;
    public OrdersAdapter(Context context, List<Orders> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Orders item = items.get(position);
        holder.nameTextView.setText(item.getName());
        holder.dateTextView.setText(item.getDate());
        holder.categoryTextView.setText(item.getCategoryes().getName());

        Glide.with(context)
                .load("https://nyfvntiwcbozwdzxguuh.supabase.co/storage/v1/object/public/image/" + item.getImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);
        // Установка цвета категории (пример)

        holder.line.setBackgroundColor(Color.parseColor(item.getCategoryes().getColor()));
 /*       try {
            holder.categoryTextView.setTextColor(Color.parseColor(item.getCategoryes().getColor()));
        } catch (IllegalArgumentException e) {
            holder.categoryTextView.setTextColor(Color.BLACK);
        }*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, dateTextView, categoryTextView;
        ImageView imageView;
        View line;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            line = itemView.findViewById(R.id.line);
        }
    }
}