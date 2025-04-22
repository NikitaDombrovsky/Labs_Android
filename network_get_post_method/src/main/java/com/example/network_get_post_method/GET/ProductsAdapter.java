package com.example.network_get_post_method.GET;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.network_get_post_method.GET.Models.Product;
import com.example.network_get_post_method.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private ArrayList<Product> productList;

    public ProductsAdapter(ArrayList<Product> eventList) {
        this.productList = eventList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textItemTitle.setText(product.getTitle());
        holder.textItemPrice.setText(String.valueOf(product.getPrice()));
        holder.textItemRating.setText(String.valueOf(product.getRating().getRate()));

        Picasso.get().load(product.getImage()).resize(250, 250).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textItemTitle;
        TextView textItemPrice;
        TextView textItemRating;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textItemTitle = itemView.findViewById(R.id.textItemTitle);
            textItemPrice = itemView.findViewById(R.id.textItemPrice);
            textItemRating = itemView.findViewById(R.id.textItemRating);
            imageView = itemView.findViewById(R.id.imageItem);
        }
    }
}
