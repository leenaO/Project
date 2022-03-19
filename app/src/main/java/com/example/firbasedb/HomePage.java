package com.example.firbasedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    RecyclerView rv2,rv1;
    Button showRecipe,showProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
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