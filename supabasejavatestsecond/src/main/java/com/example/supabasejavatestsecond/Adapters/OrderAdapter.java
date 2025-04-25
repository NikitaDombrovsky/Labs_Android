package com.example.supabasejavatestsecond.Adapters;

import static com.example.supabasejavatestsecond.SupabaseClient.SUPABASE_DOMEN;

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
import com.example.supabasejavatestsecond.Models.Order;
import com.example.supabasejavatestsecond.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    List<Order> ordersList;
    Context context;

    public OrderAdapter(Context context, List<Order> ordersList) {
        this.ordersList = ordersList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        Order order = ordersList.get(position);
        holder.nameTxV.setText(order.getName());
        holder.dateTxV.setText(order.getDate());
        holder.catNameTxV.setText(order.getCategory().getName());
        String url = SUPABASE_DOMEN + "storage/v1/object/public/imageorder/" + order.getAvatar_name();
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.imageV);
        holder.line.setBackgroundColor(Color.parseColor(order.getCategory().getColor()));
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxV, dateTxV, catNameTxV;
        ImageView imageV;
        View line;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxV = itemView.findViewById(R.id.nameTextView);
            dateTxV = itemView.findViewById(R.id.dateTextView);
            catNameTxV = itemView.findViewById(R.id.categoryTextView);
            imageV = itemView.findViewById(R.id.imageView);
            line = itemView.findViewById(R.id.line);
        }
    }
}
