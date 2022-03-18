package com.example.firbasedb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecipePage extends AppCompatActivity {
    private static final String TAG = "RecipePage";
    //CircularProgressIndicator progress_circular;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    private Context mContext;
    private Activity mActivity;
    private ArrayList<Recipe> recipeList;
    private ImageAdapter imageAdapter = null;
    ImageButton prev;
    private SearchView searchView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);

        mActivity = RecipePage.this;
        mContext = getApplicationContext();
        FirebaseApp.initializeApp(this);
        searchView=findViewById(R.id.searchBar);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        recyclerView = findViewById(R.id.rvRecipe);
        //progress_circular = findViewById(R.id.progress_circular);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);
        recipeList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("recipe");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recipeList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Recipe imagemodel = dataSnapshot.getValue(Recipe.class);
                    recipeList.add(imagemodel);
                }
                imageAdapter = new ImageAdapter(mContext,mActivity, (ArrayList<Recipe>) recipeList);
                recyclerView.setAdapter(imageAdapter);
                imageAdapter.notifyDataSetChanged();
                //progress_circular.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(RecipePage.this,"Error:" + error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        /**prev = findViewById(R.id.previousButton);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecipePage.this, HomePage.class));
            }
        });*/


    }

    private void filterList(String newText) {
        ArrayList<Recipe> filteredList=new ArrayList<>();
        for(Recipe recipe: recipeList){
            if(recipe.getRecipeName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(recipe);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"Can't find this recipe",Toast.LENGTH_LONG).show();
        }else{
            imageAdapter.setFilteredList(filteredList);

        }
    }

}