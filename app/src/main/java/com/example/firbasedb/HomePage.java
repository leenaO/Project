package com.example.firbasedb;

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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    RecyclerView rv2,rv1;
    Button showRecipe,showProduct;
    CardView kCard,lCard,vCard;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bottomNavigationView=findViewById(R.id.nav_view_h);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(HomePage.this,RecipePage.class));
                        break;
                    case R.id.nav_fav:
                        startActivity(new Intent(HomePage.this,ProductPage.class));
                        break;
                    case R.id.nav_basket:
                        startActivity(new Intent(HomePage.this,mycart.class));
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
        rv1 = (RecyclerView) findViewById(R.id.rvhome1);
        rv1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rv2 = (RecyclerView) findViewById(R.id.rvhome2);
        rv2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));



        //ArrayList<Product> products = new ArrayList<>();
        //products.add(new Product("", "Low-Fat Milk"));
        //rv1.setAdapter(new Recycler(products));

        //ArrayList<Recipe> recipes = new ArrayList<>();
        /**recipes.add(new Recipe(R.drawable.chicken, "Chicken"));
        recipes.add(new Recipe(R.drawable.breakfast, "Oat with Fruits"));
        recipes.add(new Recipe(R.drawable.sandwitch, "Chicken Sandwich"));
        recipes.add(new Recipe(R.drawable.rice, "Rice"));
        recipes.add(new Recipe(R.drawable.pasta, "Pasta"));
        rv2.setAdapter(new RecyclerViewAdapter(recipes));*/
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
    }
}