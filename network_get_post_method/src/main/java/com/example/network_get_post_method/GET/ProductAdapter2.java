package com.example.network_get_post_method.GET;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.network_get_post_method.GET.Models.Product;
import com.example.network_get_post_method.R;

import java.util.ArrayList;



public class ProductAdapter2 extends RecyclerView.Adapter<ProductAdapter2.ViewHolder> {

    private ArrayList<Product> productList;

    public ProductAdapter2(ArrayList<Product> eventList) {
        this.productList = eventList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textItemId.setText(String.valueOf(product.getId()));
        holder.textItemPrice.setText(String.valueOf(product.getPrice()));
        holder.textItemCategory.setText(String.valueOf(product.getCategory()));
        holder.textItemRate.setText(String.valueOf(product.getRating().getRate()));


        holder.textItemTitle.setText(product.getTitle());
        holder.textItemDescription.setText(String.valueOf(product.getDescription()));
        holder.textItemImage.setText("image" + String.valueOf(position + 1));
        holder.textItemCount.setText(String.valueOf(product.getRating().getCount()));

       // Picasso.get().load(product.getImage()).resize(250, 250).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textItemId, textItemPrice, textItemCategory, textItemRate;
        TextView textItemTitle, textItemDescription, textItemImage, textItemCount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textItemId = itemView.findViewById(R.id.textItemId);
            textItemPrice = itemView.findViewById(R.id.textItemPrice);
            textItemCategory = itemView.findViewById(R.id.textItemCategory);
            textItemRate = itemView.findViewById(R.id.textItemRate);

            textItemTitle = itemView.findViewById(R.id.textItemTitle);
            textItemDescription = itemView.findViewById(R.id.textItemDescription);
            textItemImage = itemView.findViewById(R.id.textItemImage);
            textItemCount = itemView.findViewById(R.id.textItemCount);

        }
    }
}
