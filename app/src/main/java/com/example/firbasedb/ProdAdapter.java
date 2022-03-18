package com.example.firbasedb;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private Activity mActivity;
    private ArrayList<Product> mContentList;

    public void setFilteredList(ArrayList<Product> productList){
        this.mContentList=productList;
        notifyDataSetChanged();

    }

    public ProdAdapter(Context mContext, Activity mActivity, ArrayList<Product> mContentList) {

        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mContentList = mContentList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_custom_item, parent, false);
        return new ViewHolder(view, viewType);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView pImg;
        private TextView pName;
        private TextView pPrice;




        public ViewHolder(View itemView, int viewType) {
            super(itemView);

            // Find all views ids
            cardView = (CardView) itemView.findViewById(R.id.productCard);
            pImg = (ImageView) itemView.findViewById(R.id.productImg);
            pName = (TextView) itemView.findViewById(R.id.productNam);
            pPrice = (TextView) itemView.findViewById(R.id.price);


        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mainHolder, int position) {
        ViewHolder holder = (ViewHolder) mainHolder;


        final Product model = mContentList.get(position);
        // setting data over views
        String imgUrl = model.getImage();
        if (imgUrl != null && !imgUrl.isEmpty()) {
            Glide.with(mContext)
                    .load(imgUrl)
                    .into(holder.pImg);
        }

        holder.pName.setText(model.getName());
        holder.pPrice.setText(model.getPrice());

    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }
}
