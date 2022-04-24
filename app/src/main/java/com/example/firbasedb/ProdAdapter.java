package com.example.firbasedb;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ProdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;

    private Activity mActivity;
    private ArrayList<Product> mContentList;

    FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
    boolean containAllergy=false;
    String allergy="null";



    public void setFilteredList(ArrayList<Product> productList){
        this.mContentList=productList;
        notifyDataSetChanged();

    }

//    public ProdAdapter(Context mContext,ArrayList<Product> mContentList) {
//        this.mContext = mContext;
//        this.mContentList = mContentList;
//    }

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
//    private void isInCart(String postid, final ImageButton imageView){
//        final FirebaseUser firebaseUser2= FirebaseAuth.getInstance().getCurrentUser();
//        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("user").child("healthinfo");
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.child(firebaseUser2.getUid()).exists()){
//
//                    imageView.setTag("in");
//                }else{
//
//                    imageView.setTag("out");
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//    private void isInCart(String productId, final ImageButton imageView){
//        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
//        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("cart").child(firebaseUser.getUid());
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.child(productId).exists()){
//
//                    imageView.setTag("in_cart");
//                }else{
//
//                    imageView.setTag("out_cart");
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

//    private void cart(String productId, final ImageButton imageView){
//        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
//        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("cart").child(firebaseUser.getUid());
//
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.child("productId").getValue().equals(productId)){
//
//                    imageView.setTag("in_cart");
//                }else{
//
//                    imageView.setTag("out_cart");
//
//                }
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
        final Product model = mContentList.get(position);
//        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.pImg.getContext())
//                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.product_details_dialog))
//                        .setExpanded(true, 1750).create();
//                View myView = dialogPlus.getHolderView();
//                TextView productName = myView.findViewById(R.id.ProductName);
//                TextView productPrice = myView.findViewById(R.id.productPrice);
//                ImageView purl = myView.findViewById(R.id.ProductImage);
//                TextView productIngredients = myView.findViewById(R.id.ProductIngredients);
//                TextView productDiet = myView.findViewById(R.id.ProductDiet);
//                ImageButton previousButton = myView.findViewById(R.id.ProductPreviousButton);
//                ImageButton favoriteButton = myView.findViewById(R.id.productFav);
//                productName.setText(model.getName());
//                productPrice.setText(model.getPrice());
//                Glide.with(mContext).load(model.getImage()).into(purl);
//                productDetails(model.getProductKey(),productIngredients,productDiet);
//
//                favoriteButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        isLike(model.getProductId(),favoriteButton);
//                        holder.addToFav.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                if(holder.addToFav.getTag().equals("like")){
//                                    FirebaseDatabase.getInstance().getReference().child("Likes").child(model.getProductId())
//                                            .child(firebaseUser.getUid()).setValue(true);
//                                }else{
//                                    FirebaseDatabase.getInstance().getReference().child("Likes").child(model.getProductId())
//                                            .child(firebaseUser.getUid()).removeValue();
//
//                                }
//                            }
//                        });
//                    }
//                });
//
//                dialogPlus.show();
//                previousButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialogPlus.dismiss();
//                    }
//                });
//
//            }
//        });



        // setting data over views
        String imgUrl = model.getImage();
        if (imgUrl != null && !imgUrl.isEmpty()) {
            Glide.with(mContext)
                    .load(imgUrl)
                    .into(holder.pImg);
        }

        holder.pName.setText(model.getName());
        holder.pPrice.setText(model.getPrice());



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
        //isInCart(model.getProductId(),holder.addToCart);
        containAllergy=false;
        holder.addToCart.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {




               DatabaseReference dbref=FirebaseDatabase.getInstance().getReference().child("user").child("healthinfo");
                dbref.addValueEventListener(new ValueEventListener() {




                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(firebaseUser.getUid()).exists()) {


                            allergy = snapshot.child(firebaseUser.getUid()).child("allrgy").getValue(String.class);

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //containAllergy=true;

                    }
                });
                ArrayList<String> ing = new ArrayList<>(Arrays.asList(model.getIngredients().split(",")));
                for (String ingredient : ing) {
                    if (ingredient.toLowerCase().trim().contains(allergy.toLowerCase().trim())){
                        containAllergy=true;
                        //holder.addToCart.setTag("allergy");
                    }
                }





                if(containAllergy){
                    Toast.makeText(mActivity, "Contains allergy" , Toast.LENGTH_SHORT).show();

                }else {

                    HashMap<String, Object> hash = new HashMap<>();

                    hash.put("img", model.getImage());
                    hash.put("name", model.getName());
                    hash.put("price", model.getPrice());
                    hash.put("productId", model.getProductId());
                    //hash.put("totalPrice",model.getPrice());
                    hash.put("productAmountInCart", "1");
                    FirebaseDatabase.getInstance().getReference().child("cart").child(firebaseUser.getUid()).child(model.getProductId()).setValue(hash)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Toast.makeText(mActivity, "Product successfully added to cart", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(mActivity, "Could not added" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }

//                if(holder.addToCart.getTag().equals("in_cart")){
//                    FirebaseDatabase.getInstance().getReference().child("cart").child(firebaseUser.getUid())
//                            .child(firebaseUser.getUid()).setValue(true);
//                }else{
//                    FirebaseDatabase.getInstance().getReference().child("Likes").child(model.getProductId())
//                            .child(firebaseUser.getUid()).removeValue();
//
//                }
                containAllergy=false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }

    private void productDetails(String productKey, final TextView productIngredients ,final TextView productDiet){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Products").child(productKey);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String proIngredients= snapshot.child("ingredients").getValue(String.class);
                productIngredients.setText(proIngredients);
                boolean ketoDiet= snapshot.child("keto").getValue(boolean.class);
                if(snapshot.child("keto").getValue(boolean.class)==true &&
                        snapshot.child("sugarFree").getValue(boolean.class)==true &&
                        snapshot.child("vegan").getValue(boolean.class)==true){
                    productDiet.setText("Keto, Sugar Free, vegan");
                }else if (snapshot.child("keto").getValue(boolean.class)==true &&
                        snapshot.child("sugarFree").getValue(boolean.class)==true &&
                        snapshot.child("vegan").getValue(boolean.class)==false){
                    productDiet.setText("Keto, Sugar Free");
                }else if (snapshot.child("keto").getValue(boolean.class)==false &&
                        snapshot.child("sugarFree").getValue(boolean.class)==true &&
                        snapshot.child("vegan").getValue(boolean.class)==true){
                    productDiet.setText("Sugar Free, vegan");
                }else if (snapshot.child("keto").getValue(boolean.class)==true &&
                        snapshot.child("sugarFree").getValue(boolean.class)==false &&
                        snapshot.child("vegan").getValue(boolean.class)==true){
                    productDiet.setText("Keto, vegan");
                }else if (snapshot.child("keto").getValue(boolean.class)==true &&
                        snapshot.child("sugarFree").getValue(boolean.class)==false &&
                        snapshot.child("vegan").getValue(boolean.class)==false){
                    productDiet.setText("Keto");
                }else if (snapshot.child("keto").getValue(boolean.class)==false &&
                        snapshot.child("sugarFree").getValue(boolean.class)==true &&
                        snapshot.child("vegan").getValue(boolean.class)==false){
                    productDiet.setText("Sugar Free");
                }else if (snapshot.child("keto").getValue(boolean.class)==false &&
                        snapshot.child("sugarFree").getValue(boolean.class)==false &&
                        snapshot.child("vegan").getValue(boolean.class)==true){
                    productDiet.setText("vegan");
                }else if (snapshot.child("keto").getValue(boolean.class)==false &&
                        snapshot.child("sugarFree").getValue(boolean.class)==false &&
                        snapshot.child("vegan").getValue(boolean.class)==false){
                    productDiet.setText("No diet suited");}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

//        cart(model.getProductId(),holder.addToCart);
//        int productsAmountInCart=0;
//        holder.addToCart.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                if(holder.addToCart.getTag().equals("in_cart")){
//                    FirebaseDatabase.getInstance().getReference().child("cart").child(model.getProductId())
//                            .child(firebaseUser.getUid()).setValue(++productsAmountInCart);
//                }
////                else{
////                    FirebaseDatabase.getInstance().getReference().child("cart").child(model.getProductId())
////                            .child(firebaseUser.getUid()).removeValue();
////
////                }
//            }
//        });






//        holder.addToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseDatabase.getInstance().getReference().child("Products").child(mContentList.get(position).getKey())
//
//            }
//        });





//    private void productAmountInCart(TextView likes,String productId){
//        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("cart")
//                .child(productId);
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
