package com.example.firbasedb;

import android.app.Activity;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity { RecyclerView rv2,rv1;
    Button showRecipe,showProduct;
    CardView kCard,lCard,vCard;
    BottomNavigationView bottomNavigationView;
    DatabaseReference databaseReference;
    ArrayList<Recipe> recipes;
    ArrayList<Product> products;
    ImageAdapter latestRecipes;
    ProdAdapter latestProducts;
    private Context mContext;
    private Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bottomNavigationView=findViewById(R.id.nav_view_h);
        mActivity = HomePage.this;
        mContext = getApplicationContext();
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(HomePage.this,HomePage.class));
                        break;
                    case R.id.nav_fav:
                        startActivity(new Intent(HomePage.this,Favorite.class));
                        break;
                    case R.id.nav_basket:
                        startActivity(new Intent(HomePage.this,CartPage.class));
                        break;
                    case R.id.nav_add_recipe:
                        startActivity(new Intent(HomePage.this,AddRecipePage.class));
                        break;
                    case R.id.nav_profile:
                        startActivity(new Intent(HomePage.this,Account.class));
                        break;


                }
                return true;
            }
        });
        kCard=(CardView) findViewById(R.id.ketoCard);
        lCard=(CardView) findViewById(R.id.lowCard);
        vCard=(CardView) findViewById(R.id.veganCard);
        kCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, KetoPage.class));
            }
        });
        lCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, SugarFreePage.class));
            }
        });
        vCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, VeganPage.class));
            }
        });

        rv1 = (RecyclerView) findViewById(R.id.rvhome1);
        rv1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rv2 = (RecyclerView) findViewById(R.id.rvhome2);
        rv2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));


        showProduct=findViewById(R.id.showProductsButton);
        showProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomePage.this,ProductPage.class));
            }
        });

        showRecipe=findViewById(R.id.showRecipeButton);
        showRecipe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomePage.this,RecipePage.class));
            }
        });


        products = new ArrayList<>();
        Query q2 = FirebaseDatabase.getInstance().getReference("Products").limitToLast(3);
        q2.addValueEventListener(valueEventListener2);
        latestProducts= new ProdAdapter(this,mActivity,products);
        rv2.setAdapter(latestProducts);

        recipes = new ArrayList<>();
        Query q =FirebaseDatabase.getInstance().getReference("recipe").limitToLast(3);
        q.addValueEventListener(valueEventListener);
        latestRecipes= new ImageAdapter(this,recipes);
        rv1.setAdapter(latestRecipes);
    }
    ValueEventListener valueEventListener= new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            recipes.clear();
            if(snapshot.exists()){
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Recipe r = dataSnapshot.getValue(Recipe.class);
                    recipes.add(r);
                }latestRecipes.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    ValueEventListener valueEventListener2= new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            products.clear();
            if(snapshot.exists()){
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    products.add(product);
                }latestProducts.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };





}