package com.example.firbasedb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    RecyclerView rv1,rv2;
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
                        startActivity(new Intent(HomePage.this,EditProfile.class));
                        break;


                }
                return true;
            }
        });
        kCard=(CardView) findViewById(R.id.ketoCard);
        lCard=(CardView) findViewById(R.id.lowCard);
        vCard=(CardView) findViewById(R.id.veganCard);

        kCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomePage.this,KetoPage.class));
            }
        });
        lCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomePage.this,SugarFreePage.class));
            }
        });
        vCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomePage.this,VeganPage.class));
            }
        });

//        rv1 = (RecyclerView) findViewById(R.id.rvhome1);
//        rv1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        rv2 = (RecyclerView) findViewById(R.id.rvhome2);
//        rv1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

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
        //latest products
        rv2=findViewById(R.id.rvhome2);
        rv2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        products = new ArrayList<>();
        Query q2 = FirebaseDatabase.getInstance().getReference("Products").limitToLast(3);
        q2.addValueEventListener(valueEventListener2);
        latestProducts= new ProdAdapter(this,mActivity,products);
        rv2.setAdapter(latestProducts);

        //latest recipes
        rv1=findViewById(R.id.rvhome1);
        rv1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recipes = new ArrayList<>();
        Query q =FirebaseDatabase.getInstance().getReference("recipe").limitToLast(3);
        q.addValueEventListener(valueEventListener);
        latestRecipes= new ImageAdapter(this,mActivity,recipes);
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




//ArrayList<Product> products = new ArrayList<>();
//products.add(new Product("", "Low-Fat Milk"));
//rv1.setAdapter(new Recycler(products));


/**recipes.add(new Recipe(R.drawable.chicken, "Chicken"));
 recipes.add(new Recipe(R.drawable.breakfast, "Oat with Fruits"));
 recipes.add(new Recipe(R.drawable.sandwitch, "Chicken Sandwich"));
 recipes.add(new Recipe(R.drawable.rice, "Rice"));
 recipes.add(new Recipe(R.drawable.pasta, "Pasta"));
 rv2.setAdapter(new RecyclerViewAdapter(recipes));*/



//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        Query lastQuery = databaseReference.orderByKey().limitToLast(1);
//        lastQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for(DataSnapshot dataSnapshot: snapshot.child("recipe").getChildren() ){
//                        Recipe r =dataSnapshot.getValue(Recipe.class);
//                        recipes.add(r);
//                    }productAdap.notifyDataSetChanged();
//                }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                throw error.toException();
//
//            }
//        });



//        productAdap = new LatestAdapter(options);
//        FirebaseRecyclerOptions<Recipe> options = new FirebaseRecyclerOptions.Builder<Recipe>().setQuery(
//                FirebaseDatabase.getInstance().getReference().child("recipe"),
//                Recipe.class).build();

//    RecyclerView rv2,rv1;
//    Button showRecipe,showProduct;
//    CardView kCard,lCard,vCard;
//    BottomNavigationView bottomNavigationView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_page);
//        bottomNavigationView=findViewById(R.id.nav_view_h);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                switch (item.getItemId()){
//                    case R.id.nav_home:
//                        startActivity(new Intent(HomePage.this,HomePage.class));
//                        break;
//                    case R.id.nav_fav:
//
//                        startActivity(new Intent(HomePage.this,Favorite.class));
//                        break;
//                    case R.id.nav_basket:
//                        startActivity(new Intent(HomePage.this,mycart.class));
//                        break;
//                    case R.id.nav_add_recipe:
//                        startActivity(new Intent(HomePage.this,AddRecipePage.class));
//                        break;
//                    case R.id.nav_profile:
//                        startActivity(new Intent(HomePage.this,EditProfile.class));
//                        break;
//
//
//                }
//                return true;
//            }
//        });
//        kCard=(CardView) findViewById(R.id.ketoCard);
//        lCard=(CardView) findViewById(R.id.lowCard);
//        vCard=(CardView) findViewById(R.id.veganCard);
//
//        kCard.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(HomePage.this,KetoPage.class));
//            }
//        });
//        lCard.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(HomePage.this,SugarFreePage.class));
//            }
//        });
//        vCard.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(HomePage.this,VeganPage.class));
//            }
//        });
//        rv1 = (RecyclerView) findViewById(R.id.rvhome1);
//        rv1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        rv2 = (RecyclerView) findViewById(R.id.rvhome2);
//        rv2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//
//
//
//        //ArrayList<Product> products = new ArrayList<>();
//        //products.add(new Product("", "Low-Fat Milk"));
//        //rv1.setAdapter(new Recycler(products));
//
//        //ArrayList<Recipe> recipes = new ArrayList<>();
//        /**recipes.add(new Recipe(R.drawable.chicken, "Chicken"));
//        recipes.add(new Recipe(R.drawable.breakfast, "Oat with Fruits"));
//        recipes.add(new Recipe(R.drawable.sandwitch, "Chicken Sandwich"));
//        recipes.add(new Recipe(R.drawable.rice, "Rice"));
//        recipes.add(new Recipe(R.drawable.pasta, "Pasta"));
//        rv2.setAdapter(new RecyclerViewAdapter(recipes));*/
//        showProduct=findViewById(R.id.showProductsButton);
//        showProduct.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(HomePage.this,ProductPage.class));
//            }
//        });
//
//        showRecipe=findViewById(R.id.showRecipeButton);
//        showRecipe.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(HomePage.this,RecipePage.class));
//            }
//        });
//    }

