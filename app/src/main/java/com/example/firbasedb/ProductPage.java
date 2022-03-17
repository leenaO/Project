package com.example.firbasedb;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductPage extends AppCompatActivity {
    private static final String TAG = "RecipePage";
    //CircularProgressIndicator progress_circular;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    private Context mContext;
    private Activity mActivity;
    private ArrayList<Recipe> imagesList;
    private ImageAdapter imageAdapter = null;
    ImageButton prev;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);

        mActivity = ProductPage.this;
        mContext = getApplicationContext();
        FirebaseApp.initializeApp(this);

        recyclerView = findViewById(R.id.rvRecipe);
        //progress_circular = findViewById(R.id.progress_circular);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);
        imagesList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("recipe");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                imagesList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Recipe imagemodel = dataSnapshot.getValue(Recipe.class);
                    imagesList.add(imagemodel);
                }
                imageAdapter = new ImageAdapter(mContext,mActivity, (ArrayList<Recipe>) imagesList);
                recyclerView.setAdapter(imageAdapter);
                imageAdapter.notifyDataSetChanged();
                //progress_circular.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ProductPage.this,"Error:" + error.getMessage(),Toast.LENGTH_SHORT).show();
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

}