//package com.example.firbasedb;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class mycart extends AppCompatActivity {
//TextView price , totaltext_price;
//Button Next;
//RecyclerView list_item;
//    DatabaseReference myRef;
//    FirebaseDatabase database;
//CartAdapter CartAdapter;
////    ImageView imageProdect;
////    TextView item_title , priceitem, quantetitem;
////    private classprodect classprodect;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mycart);
//        getSupportActionBar().hide();
//        price = (TextView) findViewById(R.id.price);
//        totaltext_price=(TextView) findViewById(R.id.totaltext_price);
//        list_item =(RecyclerView) findViewById(R.id.list_item);
//        Next = (Button) findViewById(R.id.Next);
//        list_item.setLayoutManager(new LinearLayoutManager(this));
//        database = FirebaseDatabase.getInstance();
//        FirebaseRecyclerOptions<Product> options =
//                new FirebaseRecyclerOptions.Builder<Product>()
//                        .setQuery( database.getReference("my_cart").child("Products"), Product.class)
//                        .build();
//
//
//     CartAdapter = new CartAdapter(options);
//    list_item.setAdapter(CartAdapter);
//
//
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        CartAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        CartAdapter.stopListening();
//    }
//}
////        imageProdect=(ImageView) findViewById(R.id.prodectimage);
////                item_title = (TextView) findViewById(R.id.item_title);
////                priceitem=(TextView) findViewById(R.id.priceitem);
////                quantetitem =(TextView) findViewById(R.id.quantetitem);
////                String nameitem , pricitem, uriitem,quanteitem,totlepriceitem;
////                nameitem = "ornge";
////                pricitem= "10";
////                quanteitem = "5";
////                totlepriceitem="20";
////                uriitem= "https://images.news18.com/ibnlive/uploads/2019/08/ima1.jpg?impolicy=website&width=534&height=356";
////                classprodect = new classprodect( nameitem,  pricitem, uriitem, quanteitem,totlepriceitem);
////                myRef.push().setValue(classprodect);