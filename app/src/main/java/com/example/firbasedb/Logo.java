package com.example.firbasedb;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Logo extends AppCompatActivity{



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_logo);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("message");

            myRef.setValue("Hello, World!");

            final Intent i=new Intent(Logo.this,SignIn.class);
            Thread splash_timer=new Thread(){
                public void run(){
                    try{
                        sleep(5000);

                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        startActivity(i);
                        finish();
                    }
                }
            };
            splash_timer.start();
        }



    }


