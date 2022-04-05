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

import java.util.ArrayList;

public class ProdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private Activity mActivity;
    private ArrayList<Product> mContentList;
    FirebaseUser firebaseUser;

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
        private ImageButton addToCart,addToFav;






        public ViewHolder(View itemView, int viewType) {
            super(itemView);

            // Find all views ids
            cardView = (CardView) itemView.findViewById(R.id.productCard);
            pImg = (ImageView) itemView.findViewById(R.id.productImg);
            pName = (TextView) itemView.findViewById(R.id.productNam);
            pPrice = (TextView) itemView.findViewById(R.id.price);
            addToCart=(ImageButton) itemView.findViewById(R.id.addToCart);
            addToFav=(ImageButton) itemView.findViewById(R.id.productFav);



        }

    }
    private void isLike(String postid, final ImageButton imageView){
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Likes").child(postid);
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
//    private void nrLikes(TextView likes,String postid){
//        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Likes")
//                .child(postid);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                likes.setText(snapshot.getChildren()+" likes");
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mainHolder, int position) {
        ViewHolder holder = (ViewHolder) mainHolder;
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();


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
//        holder.addToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseDatabase.getInstance().getReference().child("Products").child(mContentList.get(position).getKey())
//
//            }
//        });
        isLike(model.getProductId(),holder.addToFav);
        holder.addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.addToFav.getTag().equals("like")){
                    FirebaseDatabase.getInstance().getReference().child("Likes").child(model.getProductId())
                            .child(firebaseUser.getUid()).setValue(true);
                }else{
                    FirebaseDatabase.getInstance().getReference().child("Likes").child(model.getProductId())
                            .child(firebaseUser.getUid()).removeValue();

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }
}
