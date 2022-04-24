package com.example.firbasedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity {
    RecyclerView recyclerView,recyclerViewR;
    DatabaseReference productsReference,likesReference,likesReferenceR,recipeReference;
    private Context mContext;
    private Activity mActivity;
    FirebaseAuth firebaseAuth;
    private ArrayList<Product> productList,productFavoritList;
    private ArrayList<Recipe> recipeList,recipeFavoritList;
    private ImageAdapter imageAdapter=null;

    private ProdAdapter prodAdapter = null;
    BottomNavigationView bottomNavigationView;
    TextView kk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        bottomNavigationView=findViewById(R.id.nav_view_f);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(Favorite.this,HomePage.class));
                        break;
                    case R.id.nav_fav:

                        startActivity(new Intent(Favorite.this,Favorite.class));
                        break;
                    case R.id.nav_basket:
                        startActivity(new Intent(Favorite.this,CartPage.class));
                        break;
                    case R.id.nav_add_recipe:
                        startActivity(new Intent(Favorite.this,AddRecipePage.class));
                        break;
                    case R.id.nav_profile:
                        startActivity(new Intent(Favorite.this,EditProfile.class));
                        break;


                }
                return true;
            }
        });
        mActivity = Favorite.this;
        mContext = getApplicationContext();
        FirebaseApp.initializeApp(this);
        kk=findViewById(R.id.fp);

        recyclerView = findViewById(R.id.fav_pro_recycler);
        recyclerViewR = findViewById(R.id.fav_rec_recycler);
        //progress_circular = findViewById(R.id.progress_circular);


        firebaseAuth= FirebaseAuth.getInstance();




        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setNestedScrollingEnabled(false);
        productList=new ArrayList<>();
        productFavoritList = new ArrayList<>();

        recyclerViewR.setHasFixedSize(true);
        recyclerViewR.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewR.setNestedScrollingEnabled(false);
        recipeList=new ArrayList<>();
        recipeFavoritList = new ArrayList<>();

//
        likesReference = FirebaseDatabase.getInstance().getReference().child("Likes");
        productsReference = FirebaseDatabase.getInstance().getReference().child("Products");
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        productsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Product imagemodel = dataSnapshot.getValue(Product.class);
                    productList.add(imagemodel);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        likesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productFavoritList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    boolean b=dataSnapshot.child(firebaseUser.getUid()).exists();
                    if(b) {
                        for(Product p: productList) {

                            if (dataSnapshot.getKey().equals(p.getProductId())) {
                                //kk.setText("true");
                                productFavoritList.add(p);

                            }
                        }
                    }

                }
                prodAdapter = new ProdAdapter(mContext,mActivity, (ArrayList<Product>) productFavoritList);
                recyclerView.setAdapter(prodAdapter);
                prodAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        //=========================
        likesReferenceR = FirebaseDatabase.getInstance().getReference().child("LikesR");
        recipeReference = FirebaseDatabase.getInstance().getReference().child("recipe");


        recipeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recipeList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Recipe imagemodel = dataSnapshot.getValue(Recipe.class);
                    recipeList.add(imagemodel);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        likesReferenceR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recipeFavoritList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    boolean b=dataSnapshot.child(firebaseUser.getUid()).exists();
                    if(b) {
                        for(Recipe r: recipeList) {

                            if (dataSnapshot.getKey().equals(r.getRcipeID())) {
                                //kk.setText("true");
                                recipeFavoritList.add(r);

                            }
                        }
                    }

                }
                imageAdapter = new ImageAdapter(mContext,mActivity, (ArrayList<Recipe>) recipeFavoritList);
                recyclerViewR.setAdapter(imageAdapter);
                imageAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




//        productsReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                productFavoritList.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Product imagemodel = dataSnapshot.getValue(Product.class);
//                    String k=imagemodel.getProductId();
//
//                    //Product imagemodel = dataSnapshot.getValue(Product.class);
//
//                    likesReference.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot1) {
//                                for (DataSnapshot dataSnapshot1 : snapshot1.getChildren()) {
//                                    boolean b=dataSnapshot1.child(firebaseUser.getUid()).exists();
//                                    if(b) {
//
//                                        if (dataSnapshot1.getKey().equals(k)) {
//                                            kk.setText("true");
//                                            productFavoritList.add(imagemodel);
//
//                                        }
//                                    }
//
//                                }
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//
//
//                    //String pid=imagemodel.getProductId();
//
//
//
//
//
//
//                }
//                prodAdapter = new ProdAdapter(mContext,mActivity, (ArrayList<Product>) productFavoritList);
//                recyclerView.setAdapter(prodAdapter);
//                prodAdapter.notifyDataSetChanged();
//
//
//                //progress_circular.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//                Toast.makeText(Favorite.this,"Error:" + error.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
