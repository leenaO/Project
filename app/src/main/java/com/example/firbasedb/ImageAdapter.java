package com.example.firbasedb;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private Activity mActivity;
    private ArrayList<Recipe> mContentList;
    FirebaseUser firebaseUser;



    public void setFilteredList(ArrayList<Recipe> recipeList){
        this.mContentList=recipeList;
        notifyDataSetChanged();

    }

    public ImageAdapter(Context mContext, ArrayList<Recipe> mContentList) {

        this.mContext = mContext;
        this.mContentList = mContentList;

    }

    public ImageAdapter(Context mContext, Activity mActivity, ArrayList<Recipe> mContentList) {
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mContentList = mContentList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_custom_item,parent,false);
        return new ImageAdapter.ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView rImg;
        private TextView rName;
        private TextView rMaker;
        private ImageButton addToFav;

        public ViewHolder(View itemView) {
            super(itemView);

            // Find all views ids
            cardView = (CardView) itemView.findViewById(R.id.recipeCard);
            rImg = (ImageView) itemView.findViewById(R.id.recipeImg);
            rName = (TextView) itemView.findViewById(R.id.recipeName);
            rMaker = (TextView) itemView.findViewById(R.id.recipeMaker);
            addToFav=(ImageButton)itemView.findViewById(R.id.recipeFav);


        }

    }
    private void isLike(String postid, final ImageButton imageView){
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("RecLikes").child(postid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(firebaseUser.getUid()).exists()){
                    imageView.setImageResource(R.drawable.ic_baseline_favorite_red_24);
                    imageView.setTag("liked");
                }else{
                    imageView.setImageResource(R.drawable.ic_action_name);
                    imageView.setTag("like");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mainHolder, int position) {
        ViewHolder holder = (ViewHolder) mainHolder;
        final Recipe model = mContentList.get(position);
        // setting data over views
        String imgUrl = model.getImg();
        if (imgUrl != null && !imgUrl.isEmpty()) {
            Glide.with(mContext).load(imgUrl).into(holder.rImg);
        }

        holder.rName.setText(model.getRecipeName());
        holder.rMaker.setText(model.getRecipeMaker());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus =
                        DialogPlus.newDialog(holder.rImg.getContext())
                                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.recipe_details_dialog))
                                .setExpanded(true, 1750).create();
                View myView = dialogPlus.getHolderView();
                TextView recName = myView.findViewById(R.id.RecipeName);
                ImageView purl = myView.findViewById(R.id.recImage);
                TextView recDiet = myView.findViewById(R.id.RecipeDiet);
                TextView recProcedure = myView.findViewById(R.id.RecipeProcedure);
                ImageButton previousButton = myView.findViewById(R.id.previousButton2);
                String recKey;
                recDetails(model.getRecipeKey(),recDiet,recProcedure);

                recName.setText(model.getRecipeName());
//                recDiet.setText(model.getRecipeDiet());
//                recProcedure.setText(model.getRecipeProcedure());
                Glide.with(mContext).load(model.getImg()).into(purl);
                dialogPlus.show();
                previousButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogPlus.dismiss();
                    }
                });


            }
        });


    isLike(model.getRcipeID(),holder.addToFav);
        holder.addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.addToFav.getTag().equals("like")){
                    FirebaseDatabase.getInstance().getReference().child("RecLikes").child(model.getRcipeID())
                            .child(firebaseUser.getUid()).setValue(true);
                }else{
                    FirebaseDatabase.getInstance().getReference().child("RecLikes").child(model.getRcipeID())
                            .child(firebaseUser.getUid()).removeValue();

                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }
    private void recDetails(String recKey, final TextView recDiet, final TextView recProcedure){
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("recipe").child(recKey);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String rpro= snapshot.child("howmake").getValue(String.class);
                recProcedure.setText(rpro);
                String rdiet= snapshot.child("dite").getValue(String.class);
                recDiet.setText(rdiet);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//    private Context mContext;
//    private Activity mActivity;
//    private ArrayList<Recipe> mContentList;
//    FirebaseUser firebaseUser;
//
//    public void setFilteredList(ArrayList<Recipe> recipeList){
//        this.mContentList=recipeList;
//        notifyDataSetChanged();
//
//    }
//
//    public ImageAdapter(Context mContext, Activity mActivity, ArrayList<Recipe> mContentList) {
//
//        this.mContext = mContext;
//        this.mActivity = mActivity;
//        this.mContentList = mContentList;
//
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_custom_item, parent, false);
//        return new ViewHolder(view, viewType);
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private CardView cardView;
//        private ImageView rImg;
//        private TextView rName;
//        private TextView rMaker;
//        private ImageButton addToFav;
//
//
//        public ViewHolder(View itemView, int viewType) {
//            super(itemView);
//
//            // Find all views ids
//            cardView = (CardView) itemView.findViewById(R.id.recipeCard);
//            rImg = (ImageView) itemView.findViewById(R.id.recipeImg);
//            rName = (TextView) itemView.findViewById(R.id.recipeName);
//            rMaker = (TextView) itemView.findViewById(R.id.recipeMaker);
//            addToFav=(ImageButton)itemView.findViewById(R.id.recipeFav);
//
//        }
//    }
//        private void isLike(String postid, final ImageButton imageView){
//            final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
//            DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("RecLikes").child(postid);
//            ref.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if(snapshot.child(firebaseUser.getUid()).exists()){
//                        imageView.setImageResource(R.drawable.ic_baseline_favorite_red_24);
//                        imageView.setTag("liked");
//                    }else{
//                        imageView.setImageResource(R.drawable.ic_action_name);
//                        imageView.setTag("like");
//
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mainHolder, int position) {
//        ViewHolder holder = (ViewHolder) mainHolder;
//
//
//        final Recipe model = mContentList.get(position);
//        // setting data over views
//        String imgUrl = model.getImg();
//        if (imgUrl != null && !imgUrl.isEmpty()) {
//            Glide.with(mContext)
//                    .load(imgUrl)
//                    .into(holder.rImg);
//        }
//
//        holder.rName.setText(model.getRecipeName());
//        holder.rMaker.setText(model.getRecipeMaker());
//        isLike(model.getRcipeID(),holder.addToFav);
//        holder.addToFav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(holder.addToFav.getTag().equals("like")){
//                    FirebaseDatabase.getInstance().getReference().child("RecLikes").child(model.getRcipeID())
//                            .child(firebaseUser.getUid()).setValue(true);
//                }else{
//                    FirebaseDatabase.getInstance().getReference().child("RecLikes").child(model.getRcipeID())
//                            .child(firebaseUser.getUid()).removeValue();
//
//                }
//            }
//        });
//
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mContentList.size();
//    }


}
