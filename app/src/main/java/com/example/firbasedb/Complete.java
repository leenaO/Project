package com.example.firbasedb;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Complete extends AppCompatActivity {

    private ArrayList<Cart> complete=new ArrayList<>();
    Button b;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_order);

        b2=findViewById(R.id.got);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//                DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("cart");
//                DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("cart").child(firebaseUser.getUid());
//                DatabaseReference accountRef = FirebaseDatabase.getInstance().getReference().child("Account");
//                ArrayList<Cart> userCart = new ArrayList<>();
//                ArrayList<String> accountId = new ArrayList<>();
//                accountRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//
//                            if (!(dataSnapshot.getKey().equals(db2.getKey()))) {
//                                accountId.add(dataSnapshot.getKey());
//                            }
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//                db2.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                            Cart uCart = dataSnapshot.getValue(Cart.class);
//                            userCart.add(uCart);
//
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//                if(userCart!=null&&userCart.size()>0){
//                db.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                            for (String id : accountId) {
//                                if (dataSnapshot.getKey().equals(id)) {
//                                    Iterable<DataSnapshot> productsInCart = dataSnapshot.getChildren();
//                                    for (DataSnapshot a : productsInCart) {
//                                        for (Cart b : userCart) {
//                                            if (b.getProductId().equals(a.getKey())) {
//
//                                                String amount = (Integer.parseInt(b.getAmount()) - Integer.parseInt(b.getProductAmountInCart())) + "";
//                                            Map<String, Object> map2 = new HashMap<>();
//                                            if (Integer.parseInt(amount) == 0) {
//                                                db.child(dataSnapshot.getKey()).child(a.getKey()).removeValue();
//
//                                            } else {
//                                                String aCart=a.child("productAmountInCart").getValue().toString();
//                                                if (Integer.parseInt(aCart) > Integer.parseInt(amount)) {
//                                                    map2.put("productAmountInCart", amount);
//
//                                                }
//                                                map2.put("amount", amount);
//                                                db.child(dataSnapshot.getKey()).child(a.getKey()).updateChildren(map2);
//                                            }
//                                            //========================
//
//                                            }
//
//                                        }
//
//                                    }
//
//                                }
//
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//                    ArrayList<Product> ap=new ArrayList<>();
//                    DatabaseReference dbp = FirebaseDatabase.getInstance().getReference().child("Products");
//                    dbp.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                                Product p = dataSnapshot.getValue(Product.class);
//                                //ap.add(p);
//                                for (Cart c : userCart) {
//                                    if (p.getProductId().equals(c.getProductId())) {
//                                        Map<String, Object> map = new HashMap<>();
//                                        String amount = (Integer.parseInt(c.getAmount()) - Integer.parseInt(c.getProductAmountInCart())) + "";
//                                        map.put("amount", amount);
//                                        dbp.child(p.getProductKey()).updateChildren(map);
//
//
//                                    }
//                                }
//
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//                    //====================
//                    db2.removeValue();
//
//
//            }
//
//            }
//        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Complete.this,HomePage.class));
            }
        });

    }


}
