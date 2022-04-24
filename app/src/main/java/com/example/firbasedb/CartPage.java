package com.example.firbasedb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;





public class CartPage extends AppCompatActivity {
    private static final String TAG = "CartPage";
    DatabaseReference databaseReference;
    private Context mContext;
    private Activity mActivity;
    private ArrayList<Cart> cartList;
    private CartAd cartAd = null;
    BottomNavigationView bottomNavigationView;
//    ImageButton prev;
//    private SearchView searchView;
    RecyclerView recyclerView;
    TextView totalPriceCart;
    double total=0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mActivity = CartPage.this;
        mContext = getApplicationContext();
        FirebaseApp.initializeApp(this);

        bottomNavigationView=findViewById(R.id.nav_view_cart);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(CartPage.this,HomePage.class));
                        break;
                    case R.id.nav_fav:
                        startActivity(new Intent(CartPage.this,Favorite.class));
                        break;
                    case R.id.nav_basket:
                        startActivity(new Intent(CartPage.this,CartPage.class));
                        break;
                    case R.id.nav_add_recipe:
                        startActivity(new Intent(CartPage.this,AddRecipePage.class));
                        break;
                    case R.id.nav_profile:
                        startActivity(new Intent(CartPage.this,EditProfile.class));
                        break;


                }
                return true;
            }
        });
//        searchView=findViewById(R.id.searchBar);
//        searchView.clearFocus();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterList(newText);
//                return true;
//
//            }
//        });
//        tt=findViewById(R.id.totalPriceCart);
        totalPriceCart=(TextView) findViewById(R.id.totalPriceCart);

        recyclerView = findViewById(R.id.cartRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);
        cartList = new ArrayList<>();
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();



        databaseReference = FirebaseDatabase.getInstance().getReference().child("cart").child(firebaseUser.getUid());
        String key = FirebaseDatabase.getInstance().getReference().child("cart").child(firebaseUser.getUid()).getKey();
//        tt.setText(firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cartList.clear();
                if(snapshot.getKey().equals(firebaseUser.getUid())) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){


                        double n=Double.parseDouble(dataSnapshot.child("price").getValue().toString());
                        double a=Double.parseDouble(dataSnapshot.child("productAmountInCart").getValue().toString());
                        if(a==1) {
                            total += n;
                        }else{
                            total= total+(a*n);
                        }


                        Cart imagemodel = dataSnapshot.getValue(Cart.class);


                        cartList.add(imagemodel);
                    }
                    totalPriceCart.setText(total+"");
                Cart.totalPrice=total;
                total=0;
                }
                cartAd = new CartAd(mContext,mActivity, (ArrayList<Cart>) cartList);
                recyclerView.setAdapter(cartAd);
                cartAd.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        prev = findViewById(R.id.previousButton);
//        prev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(RecipePage.this, HomePage.class));
//            }
//        });


    }






//    private void filterList(String newText) {
//        ArrayList<Recipe> filteredList=new ArrayList<>();
//        for(Recipe recipe: recipeList){
//            if(recipe.getRecipeName().toLowerCase().contains(newText.toLowerCase())){
//                filteredList.add(recipe);
//            }
//        }
//        if(filteredList.isEmpty()){
//            Toast.makeText(this,"Can't find this recipe",Toast.LENGTH_LONG).show();
//        }else{
//            imageAdapter.setFilteredList(filteredList);
//
//        }
//    }


}
