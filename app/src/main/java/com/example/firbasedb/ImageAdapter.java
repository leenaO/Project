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

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private Activity mActivity;
    private ArrayList<Recipe> mContentList;

    public ImageAdapter(Context mContext, Activity mActivity, ArrayList<Recipe> mContentList) {

        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mContentList = mContentList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_custom_item, parent, false);
        return new ViewHolder(view, viewType);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView rImg;
        private TextView rName;
        private TextView rMaker;



        public ViewHolder(View itemView, int viewType) {
            super(itemView);

            // Find all views ids
            cardView = (CardView) itemView.findViewById(R.id.card_view_top);
            rImg = (ImageView) itemView.findViewById(R.id.recipeImg);
            rName = (TextView) itemView.findViewById(R.id.recipeName);
            rMaker = (TextView) itemView.findViewById(R.id.recipeMaker);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mainHolder, int position) {
        ViewHolder holder = (ViewHolder) mainHolder;


        final Recipe model = mContentList.get(position);
        // setting data over views
        String imgUrl = model.getImg();
        if (imgUrl != null && !imgUrl.isEmpty()) {
            Glide.with(mContext)
                    .load(imgUrl)
                    .into(holder.rImg);
        }

        holder.rName.setText(model.getRecipeName());
        holder.rMaker.setText(model.getRecipeMaker());

    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }
}
