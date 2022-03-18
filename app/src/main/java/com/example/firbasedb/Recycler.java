package com.example.firbasedb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycler extends RecyclerView.Adapter<Recycler.ProductViewHolder>{
    ArrayList<Product> products;
    public Recycler(ArrayList<Product> products){
        this.products=products;

    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_custom_item,null,false);
        ProductViewHolder viewHolder=new ProductViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product p=products.get(position);
        //holder.productImg.setImageResource(p.getImage());
        holder.productName.setText(p.getName());


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView productName,price;
        ImageView productImg;
        ImageButton recFav;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.productName);
            productImg=itemView.findViewById(R.id.productImg);

        }
    }
}
