package com.example.firbasedb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class healthinfo extends AppCompatActivity {

    Button sedentaryActive , lightlyActive , moderatelyActive , veryActive , extraActive, female, male,confirm;
    TextView calories,skip;
    EditText age , height , weight ;
    Double dAge , dHeight, dWeight;
    CheckBox milk,egg,peanut,soy,wheat,nut;
    String allergy="";
    final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
    private boolean isPressed = false ;
    Double result =0.0;
    String gender="";
    String active="";
    boolean flag = true;
    double a=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healtheinfo);
        skip = (TextView) findViewById(R.id.skip);

        sedentaryActive = (Button) findViewById(R.id.button4);

        lightlyActive = (Button) findViewById(R.id.button5);
        moderatelyActive = (Button) findViewById(R.id.button6);
        veryActive = (Button) findViewById(R.id.button7);
        extraActive = (Button) findViewById(R.id.button8);
        confirm = (Button) findViewById(R.id.confirm);
        age = (EditText) findViewById(R.id.age);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        female = (Button) findViewById(R.id.female);
        male = (Button) findViewById(R.id.male);
        calories = (TextView) findViewById(R.id.calories);
        milk=findViewById(R.id.checkMilk);
        egg=findViewById(R.id.checkEgg);
        peanut=findViewById(R.id.checkPeanut);
        soy=findViewById(R.id.checkSoy);
        wheat=findViewById(R.id.checkWheat);
        nut=findViewById(R.id.checkNut);
        Button[] activeButtons={sedentaryActive,lightlyActive,moderatelyActive,veryActive,extraActive};
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("user").child("healthinfo");
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(firebaseUser.getUid()).exists()){
                    //for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                        profile p = snapshot.child(firebaseUser.getUid()).getValue(profile.class);
                        age.setText(p.getAge().toString());
                        height.setText(p.getHeight().toString());
                        weight.setText(p.getWeight().toString());
                        gender = p.getGender().toString();
                        active = p.getActive().toString();
                        result = Double.parseDouble(p.getCalorie().toString());
                        long r = Math.round(result);
                        calories.setText(String.valueOf(r));
                        String ar = p.getAllergy().toString();
                        ArrayList<String> aller = new ArrayList<>(Arrays.asList(ar.split(",")));
                        for (String al : aller){
                            if (al.toLowerCase().trim().equals("milk")) {
                                milk.setTag("true");
                                milk.setChecked(true);
                            }
                        if (al.toLowerCase().trim().equals("egg")) {
                            milk.setTag("true");
                            egg.setChecked(true);
                        }
                        if (al.toLowerCase().trim().equals("peanut")) {
                            milk.setTag("true");
                            peanut.setChecked(true);
                        }
                        if (al.toLowerCase().trim().equals("soy")) {
                            milk.setTag("true");
                            soy.setChecked(true);
                        }
                        if (al.toLowerCase().trim().equals("wheat")) {
                            milk.setTag("true");
                            wheat.setChecked(true);
                        }
                        if (al.toLowerCase().trim().equals("nut")) {
                            milk.setTag("true");
                            nut.setChecked(true);
                        }
                    }
                        if(active.equals("sedentaryActive")){
                            color(sedentaryActive,activeButtons);
                            a=1.2;
                        }else if(active.equals("lightlyActive")){
                            color(lightlyActive,activeButtons);
                            a=1.375;
                        }else if(active.equals("moderatelyActive")){
                            color(moderatelyActive,activeButtons);
                            a=1.55;
                        }else if(active.equals("veryActive")){
                            color(veryActive,activeButtons);
                            a=1.725;

                        }else if(active.equals("extraActive")){
                            color(extraActive,activeButtons);
                            a=1.9;
                        }

                        //}




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        gender = "female";
                        female.setPressed(true);

            }
        });
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "male";
                male.setPressed(true);

            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),HomePage.class));
            }
        });


        sedentaryActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                active="sedentaryActive";
                color(sedentaryActive,activeButtons);
                a=1.2;

            }
        });
        lightlyActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                active="lightlyActive";
                color(lightlyActive,activeButtons);
                a=1.375;

            }
        });
        moderatelyActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                active="moderatelyActive";
                color(moderatelyActive,activeButtons);
                a=1.55;

            }
        });
        veryActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                active="veryActive";
                color(veryActive,activeButtons);
                a=1.725;

            }
        });
        extraActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                active="extraActive";
                color(extraActive,activeButtons);
                a=1.9;

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!gender.equals("")&&!(age.getText().toString().isEmpty())&&!(height.getText().toString().isEmpty())&&!(weight.getText().toString().isEmpty())&&!active.equals("")){

                    dAge=Double.parseDouble(age.getText().toString());
                    dHeight=Double.parseDouble(height.getText().toString());
                    dWeight=Double.parseDouble(weight.getText().toString());
                    if(gender.equals("female")){
                        result= 655.1 + (9.563 * dWeight) + (1.85 * dHeight) - (4.676 * dAge);
                        result *=a;

                    }else{
                        result = 66.47 + (13.75 * dWeight) + (5.003 * dHeight) - (6.755 * dAge);
                        result *=a;
                    }
                    long r=Math.round(result);

//                    if(milk.getTag().equals("true")&&milk.isPressed()){
//                        milk.setChecked(false);
//                    }
//                    if(egg.getTag().equals("true")&&egg.isPressed()){
//                        egg.setChecked(false);
//                    }
//                    if(peanut.getTag().equals("true")&&peanut.isPressed()){
//                        peanut.setChecked(false);
//                    }
//                    if(soy.getTag().equals("true")&&soy.isPressed()){
//                        soy.setChecked(false);
//                    }
//                    if(wheat.getTag().equals("true")&&wheat.isPressed()){
//                        wheat.setChecked(false);
//                    }
//                    if(nut.getTag().equals("true")&&nut.isPressed()){
//                        nut.setChecked(false);
//                    }










                List<String> allergyList = new ArrayList<>();
                if(milk.isChecked())
                    allergyList.add("milk");
                if(egg.isChecked())
                    allergyList.add("egg");
                if(peanut.isChecked())
                    allergyList.add("peanut");
                if(soy.isChecked())
                    allergyList.add("soy");
                if(wheat.isChecked())
                    allergyList.add("wheat");
                if(nut.isChecked())
                    allergyList.add("nut");


                if(!allergyList.isEmpty()) {
                    for (String al : allergyList) {
                        allergy = al + "," + allergy;
                        Toast.makeText(getApplicationContext(),"Insert successfully",Toast.LENGTH_LONG).show();
                    }

                    allergy = allergy.substring(0, allergy.length() - 1);
                }
                DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("user").child("healthinfo");
                FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       // if(snapshot.child(firebaseUser.getUid()).exists()){
//                            HashMap<String,Object> map=new HashMap<>();
//                            map.put("age",age.getText().toString());
//                            map.put("height",height.getText().toString());
//                            map.put("weight",weight.getText().toString());
//                            map.put("active",active);
//                            map.put("allergy",allergy);
//                            map.put("gender",gender);
//                            map.put("calorie",String.valueOf(r));
//                            ref.child(firebaseUser.getUid()).updateChildren(map);



                        //}else{
                            HashMap<String,Object> map=new HashMap<>();
                            map.put("age",age.getText().toString());
                            map.put("height",height.getText().toString());
                            map.put("weight",weight.getText().toString());
                            map.put("active",active);
                            map.put("allergy",allergy);
                            map.put("gender",gender);
                            map.put("calorie",String.valueOf(r));
                            ref.child(firebaseUser.getUid()).setValue(map);

                        //}

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                startActivity(new Intent(healthinfo.this,HomePage.class));


            }else{
                Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_LONG).show();
            }}
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(healthinfo.this, HomePage.class));
            }
        });





    }
    private void color(Button b,Button[] c) {
        for (int i = 0; i < c.length; i++) {
            c[i].setBackground(getResources().getDrawable(R.drawable.button_circle));
        }

        b.setBackground(getResources().getDrawable(R.drawable.button_custom));
    }


//    public void Calculate(View view) {
//
//
//
//
//
//
//
////
//        startActivity(new Intent(healthinfo.this, HomePage.class));
//
//    }
}