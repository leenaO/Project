package com.example.firbasedb;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KhiarAddPage extends AppCompatActivity {
    EditText name,price,amount;
    Button add;
    Spinner section;
    FirebaseDatabase database;
    DatabaseReference reference;
    Product product;
    int mixed =0;
    int request_code;
    Uri uri;
    ImageView imageView;
    ActivityResultLauncher<Intent> someActivityResultLauncher1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khiar_add_page);
        Spinner mySpin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myAdpt = new ArrayAdapter<String>(KhiarAddPage.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Section));
        myAdpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpin.setAdapter(myAdpt);

        product= new Product();
        name=(EditText) findViewById(R.id.addpname);
        price=(EditText) findViewById(R.id.addpprice);
        amount=(EditText) findViewById(R.id.addpamount);
        section=(Spinner) findViewById(R.id.spinner);
        /**imageView = (ImageView) findViewById(R.id.fPicture);
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,2);
            }
        });*/
        add=(Button)findViewById(R.id.addp);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productInsert();
            }
        });



    }

    private void productInsert() {
        Map<String,Object> map= new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("price",price.getText().toString());
        map.put("amount",amount.getText().toString());
        map.put("section",section.getSelectedItem().toString());
        FirebaseDatabase.getInstance().getReference().child("Products").push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        name.setText("");
                        price.setText("");
                        amount.setText("");
                        Toast.makeText(getApplicationContext(), "Product successfully added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Could not added", Toast.LENGTH_SHORT).show();

            }
        });



    }


}
