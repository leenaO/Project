package com.example.firbasedb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ProductPage extends AppCompatActivity {
    private static final String TAG = "ProductPage";
    //CircularProgressIndicator progress_circular;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    private Context mContext;
    private Activity mActivity;
    private ArrayList<Product> productsList;
    private ProdAdapter prodAdapter = null;
    ImageButton prev,addToCart;
    private SearchView searchView;
    FirebaseAuth firebaseAuth;
    BottomNavigationView bottomNavigationView;
    CardView productCard;


    Button all,frozen,cann,dairy,fresh,snacks,drinks;
    ArrayList<Product> filteredSection;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        bottomNavigationView=findViewById(R.id.nav_view_p);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(ProductPage.this,HomePage.class));
                        break;
                    case R.id.nav_fav:

                        startActivity(new Intent(ProductPage.this,Favorite.class));
                        break;
                    case R.id.nav_basket:
                        startActivity(new Intent(ProductPage.this,mycart.class));
                        break;
                    case R.id.nav_add_recipe:
                        startActivity(new Intent(ProductPage.this,AddRecipePage.class));
                        break;
                    case R.id.nav_profile:
                        startActivity(new Intent(ProductPage.this,EditProfile.class));
                        break;


                }
                return true;
            }
        });

        mActivity = ProductPage.this;
        mContext = getApplicationContext();
        FirebaseApp.initializeApp(this);

//        productCard=findViewById(R.id.productCard);
//        productCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ProductPage.this,HealthNutrition.class));
//            }
//        });

        all=findViewById(R.id.allProduct);
        frozen=findViewById(R.id.frozen);
        cann=findViewById(R.id.canned);
        dairy=findViewById(R.id.dairy);
        fresh=findViewById(R.id.fresh);
        snacks=findViewById(R.id.snack);
        drinks=findViewById(R.id.drinks);
        Button[] category={all,frozen,cann,dairy,fresh,snacks,drinks};



        all.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                section("all",all,category);
            }
        });

        frozen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                section("frozen",frozen,category);
            }
        });

        cann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                section("canned",cann,category);
            }
        });

        dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                section("dairy",dairy,category);
            }
        });

        fresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                section("fresh",fresh,category);
            }
        });
        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                section("snacks",snacks,category);
            }
        });
        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                section("drinks",drinks,category);
            }
        });



        recyclerView = findViewById(R.id.rvProduct);
        //progress_circular = findViewById(R.id.progress_circular);
//        addToCart=findViewById(R.id.addToCart);
//
//        firebaseAuth=FirebaseAuth.getInstance();
//        firebaseAuth.getUid();
//        addToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                databaseReference =
//                        FirebaseDatabase.getInstance().getReference("my_cart").child("Products");
//                DatabaseReference df=FirebaseDatabase.getInstance().getReference("Products");
//                df.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        String key=snapshot.getKey();
//                        Map<String,Object> map= new HashMap<>();
//
//                        map.put("key",key);
//                        databaseReference.push().setValue(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//
//                                        Toast.makeText(getApplicationContext(), "Product successfully added to the cart", Toast.LENGTH_SHORT).show();
//                                    }
//                                }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(getApplicationContext(), "Could not added to the cart"+e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });






            //}
        //});





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
                filteredSection=productsList;
                //progress_circular.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ProductPage.this,"Error:" + error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

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
        prev = findViewById(R.id.previousButtonProduct);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductPage.this, HomePage.class));
            }
        });


    }



    private void filterList(String newText) {
        ArrayList<Product> filteredList=new ArrayList<>();
        for(Product product: filteredSection){
            if(product.getName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(product);
            }
        }
        if(filteredList.isEmpty()){
            prodAdapter.setFilteredList(filteredList);
            Toast.makeText(this,"Can't find this product",Toast.LENGTH_LONG).show();
        }else{
            prodAdapter.setFilteredList(filteredList);

        }
    }
    private void section(String sec,Button b,Button[] c){
        for(int i=0;i<c.length;i++){
            c[i].setBackground(getResources().getDrawable(R.drawable.button_categ2));
        }

        b.setBackground(getResources().getDrawable(R.drawable.button_categ));


        filteredSection=new ArrayList<>();
        if((sec.toLowerCase()).equals("all")){
            filteredSection=productsList;
        }else{
            for(Product product: productsList){
                if(product.getSection().toLowerCase().contains(sec.toLowerCase())){
                    filteredSection.add(product);
                }
            }}
        if(filteredSection.isEmpty()){
            prodAdapter.setFilteredList(filteredSection);
            Toast.makeText(ProductPage.this,"There is no products in "+sec+" section",Toast.LENGTH_LONG).show();
        }else{
            prodAdapter.setFilteredList(filteredSection);

        }

    }
//    public void addTo(View view) {
//        DatabaseReference df=FirebaseDatabase.getInstance().getReference().child("Products");
//        df.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//// String ss = snapshot.getKey();
//                for (DataSnapshot s : snapshot.getChildren()) {
//                    String key = s.getKey();
//                    HashMap<String, Object> map = new HashMap<>();
//                    map.put("key", key);
//                    map.put("id", firebaseAuth.getUid());
//                    FirebaseDatabase.getInstance().getReference().child("my_cart").push().setValue(map); } }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) { } }); }



}




