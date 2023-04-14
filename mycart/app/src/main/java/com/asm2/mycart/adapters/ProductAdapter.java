package com.asm2.mycart.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asm2.mycart.R;
import com.asm2.mycart.models.Product;

import java.util.ArrayList;
import java.util.List;

    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> products;
    private int maxWord = 15;

    // Constructor for the adapter
    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }


    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        String[] word = TextUtils.split(product.getName(), "\\s+");

        StringBuilder truncatedName = new StringBuilder();
        for (int i = 0; i < Math.min(maxWord, word.length); i++) {
            truncatedName.append(word[i]).append(" ");
        }

        if (truncatedName.length() > maxWord) {
            truncatedName.append("...");
        }
        holder.productNameTextView.setText(truncatedName.toString());

        holder.productPriceTextView.setText(String.valueOf(product.getPrice()));
        holder.productImageView.setImageResource(product.getUrl());
        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if (products == null) {
            return 0;
        }
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView productNameTextView;
        TextView productPriceTextView;
        ImageView productImageView;
        ImageButton addToCartButton;

        public ViewHolder(@NonNull View parent) {
            super(parent);
            productNameTextView = parent.findViewById(R.id.productName);
            productPriceTextView = parent.findViewById(R.id.productPrice);
            productImageView = parent.findViewById(R.id.productImage);
            addToCartButton = parent.findViewById(R.id.addCart);
        }
    }
}
