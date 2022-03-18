package com.example.firbasedb;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ProductPage extends AppCompatActivity {
    private static final String TAG = "ProductPage";
    //CircularProgressIndicator progress_circular;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    private Context mContext;
    private Activity mActivity;
    private ArrayList<Product> productsList;
    private ProdAdapter prodAdapter = null;
    ImageButton prev;
    private SearchView searchView;
    Button frozen,cann,dairy,fresh;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        mActivity = ProductPage.this;
        mContext = getApplicationContext();
        FirebaseApp.initializeApp(this);
        searchView=findViewById(R.id.searchBarProduct);
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
        frozen=findViewById(R.id.frozen);
        frozen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                section("frozen");
            }
        });
        cann=findViewById(R.id.canned);
        cann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                section("canned");
            }
        });
        dairy=findViewById(R.id.dairy);
        cann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                section("dairy");
            }
        });
        fresh=findViewById(R.id.fresh);
        fresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                section("fresh");
            }
        });


        recyclerView = findViewById(R.id.rvProduct);
        //progress_circular = findViewById(R.id.progress_circular);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);
        productsList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Products");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productsList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product imagemodel = dataSnapshot.getValue(Product.class);
                    productsList.add(imagemodel);
                }
                prodAdapter = new ProdAdapter(mContext,mActivity, (ArrayList<Product>) productsList);
                recyclerView.setAdapter(prodAdapter);
                prodAdapter.notifyDataSetChanged();
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
    private void filterList(String newText) {
        ArrayList<Product> filteredList=new ArrayList<>();
        for(Product product: productsList){
            if(product.getName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(product);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"Can't find this product",Toast.LENGTH_LONG).show();
        }else{
            prodAdapter.setFilteredList(filteredList);

        }
    }
    private void section(String sec){
        ArrayList<Product> filteredList=new ArrayList<>();
        for(Product product: productsList){
            if(product.getSection().toLowerCase().contains(sec.toLowerCase())){
                filteredList.add(product);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(ProductPage.this,"Can't find this product",Toast.LENGTH_LONG).show();
        }else{
            prodAdapter.setFilteredList(filteredList);

        }

    }

}