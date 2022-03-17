package com.example.firbasedb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class healthinfo extends Activity implements  View.OnClickListener ,AdapterView.OnItemSelectedListener{

Button buttonstandr , buttonlightly , buttonmodraty , buttonvery , buttonexra, female, male,confirm;
TextView textfirst , textsecond , textthired , textforth , textfifth , res;
EditText Age , height , wight ;
Double age , entrheight, entrwight;
    String item;
profile healthinfo;
    DatabaseReference myRef;
    FirebaseDatabase database;
    private boolean isPressed = false ;
    Double result =0.0 , result1 =0.0;
    String gender="null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healtheinfo);
        buttonstandr = (Button) findViewById(R.id.button4);
        textfirst = (TextView) findViewById(R.id.textView19);
        buttonstandr.setOnClickListener(this);
        buttonlightly = (Button) findViewById(R.id.button5);
        textsecond = (TextView) findViewById(R.id.textView20);
        buttonlightly.setOnClickListener(this);
        buttonmodraty = (Button) findViewById(R.id.button6);
        textthired = (TextView) findViewById(R.id.textView21);
        buttonmodraty.setOnClickListener(this);
        buttonvery = (Button) findViewById(R.id.button7);
        textforth = (TextView) findViewById(R.id.textView22);
        buttonvery.setOnClickListener(this);
        buttonexra = (Button) findViewById(R.id.button8);
        textfifth = (TextView) findViewById(R.id.textView23);
        buttonexra.setOnClickListener(this);
        confirm = (Button) findViewById(R.id.button);
        Age = (EditText) findViewById(R.id.Age);
        height = (EditText) findViewById(R.id.height);
        wight = (EditText) findViewById(R.id.weight);
        female = (Button) findViewById(R.id.female);
        male = (Button) findViewById(R.id.male);
        res = (TextView) findViewById(R.id.textView24);
        female.setOnClickListener(this);
        male.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.Spinner);

        spinner.setOnItemSelectedListener( this);

        List<String> allergy = new ArrayList<String>();
        allergy.add("Milk");
        allergy.add("Eggs");
        allergy.add("peanuts");
        allergy.add("tree nuts");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allergy);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

//        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoComplete);
//        dataAdapter1 = new ArrayAdapter<String>(this, R.layout.list_item_dropdown, itemallrgy);
//        autoCompleteTextView.setAdapter(dataAdapter1);
//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getApplicationContext(),"item"+item,Toast.LENGTH_LONG).show();
//
//
//            }
//        });



    }





    @Override
    public void onClick(View view) {
        age = Double.valueOf(Age.getText().toString());
        entrwight = Double.valueOf(wight.getText().toString());
        entrheight = Double.valueOf(height.getText().toString());

        switch (view.getId()) {
            case R.id.female:
                gender = "female";
                female.setPressed(true);
                result1 = 655.1 + (9.563 * entrwight) + (1.85 * entrheight) - (4.676 * age);
                res.setText(String.valueOf(result1));
                break;

            case R.id.male:
                gender="male";
                male.setPressed(true);
                result = 66.47 + (13.75 * entrwight) + (5.003 * entrheight) - (6.755 * age);
                //Toast.makeText(getApplicationContext(),"the result"+result,Toast.LENGTH_LONG).show();
                res.setText(String.valueOf(result));
                break;
        }



        switch (view.getId()) {
            case R.id.button4:


                if (isPressed == false) {

                    buttonstandr.setBackgroundResource(R.drawable.button_custom);
                    textfirst.setVisibility(View.VISIBLE);
                    isPressed = true;
                    textsecond.setVisibility(View.INVISIBLE);
                    textthired.setVisibility(View.INVISIBLE);
                    textforth.setVisibility(View.INVISIBLE);
                    textfifth.setVisibility(View.INVISIBLE);
                    buttonmodraty.setBackgroundResource(R.drawable.button_circle);
                    buttonvery.setBackgroundResource(R.drawable.button_circle);
                    buttonexra.setBackgroundResource(R.drawable.button_circle);
                    buttonlightly.setBackgroundResource(R.drawable.button_circle);
                    res.setText(String.valueOf(result*1.2));
                    res.setText(String.valueOf(result1*1.2));



                } else if (isPressed == true) {

                    buttonstandr.setBackgroundResource(R.drawable.button_circle);
                    textfirst.setVisibility(View.INVISIBLE);
                    isPressed = false;
                }


                break;
            case R.id.button5:


                if (isPressed == false) {
                    buttonlightly.setBackgroundResource(R.drawable.button_custom);
                    textsecond.setVisibility(View.VISIBLE);
                    isPressed = true;
                    textfirst.setVisibility(View.INVISIBLE);
                    textthired.setVisibility(View.INVISIBLE);
                    textforth.setVisibility(View.INVISIBLE);
                    textfifth.setVisibility(View.INVISIBLE);
                    buttonstandr.setBackgroundResource(R.drawable.button_circle);
                    buttonmodraty.setBackgroundResource(R.drawable.button_circle);
                    buttonvery.setBackgroundResource(R.drawable.button_circle);
                    buttonexra.setBackgroundResource(R.drawable.button_circle);
                    res.setText(String.valueOf(result*1.375));
                    res.setText(String.valueOf(result1*1.375));

                } else if (isPressed == true) {
                    buttonlightly.setBackgroundResource(R.drawable.button_circle);
                    textsecond.setVisibility(View.INVISIBLE);
                    isPressed = false;
                }


                break;
            case R.id.button6:
                if (isPressed == false) {
                    buttonmodraty.setBackgroundResource(R.drawable.button_custom);
                    textthired.setVisibility(View.VISIBLE);
                    isPressed = true;
                    textsecond.setVisibility(View.INVISIBLE);
                    textforth.setVisibility(View.INVISIBLE);
                    textfifth.setVisibility(View.INVISIBLE);
                    textfirst.setVisibility(View.INVISIBLE);
                    buttonvery.setBackgroundResource(R.drawable.button_circle);
                    buttonexra.setBackgroundResource(R.drawable.button_circle);
                    buttonlightly.setBackgroundResource(R.drawable.button_circle);
                    buttonstandr.setBackgroundResource(R.drawable.button_circle);
                    res.setText(String.valueOf(result*1.55));
                    res.setText(String.valueOf(result1*1.55));


                } else {
                    buttonmodraty.setBackgroundResource(R.drawable.button_circle);
                    textthired.setVisibility(View.INVISIBLE);
                    isPressed = false;
                }


                break;
            case R.id.button7:

                if (isPressed == false) {
                    buttonvery.setBackgroundResource(R.drawable.button_custom);
                    textforth.setVisibility(View.VISIBLE);
                    isPressed = true;
                    textthired.setVisibility(View.INVISIBLE);
                    textfirst.setVisibility(View.INVISIBLE);
                    textsecond.setVisibility(View.INVISIBLE);
                    textfifth.setVisibility(View.INVISIBLE);
                    buttonexra.setBackgroundResource(R.drawable.button_circle);
                    buttonlightly.setBackgroundResource(R.drawable.button_circle);
                    buttonstandr.setBackgroundResource(R.drawable.button_circle);
                    buttonmodraty.setBackgroundResource(R.drawable.button_circle);
                    res.setText(String.valueOf(result*1.725));
                    res.setText(String.valueOf(result1*1.725));

                } else if (isPressed == true) {
                    buttonvery.setBackgroundResource(R.drawable.button_circle);
                    textforth.setVisibility(View.INVISIBLE);
                    isPressed = false;

                }


                break;
            case R.id.button8:

                if (isPressed == false) {
                    buttonexra.setBackgroundResource(R.drawable.button_custom);
                    textfifth.setVisibility(View.VISIBLE);
                    isPressed = true;
                    textforth.setVisibility(View.INVISIBLE);
                    textthired.setVisibility(View.INVISIBLE);
                    textfirst.setVisibility(View.INVISIBLE);
                    textsecond.setVisibility(View.INVISIBLE);
                    buttonlightly.setBackgroundResource(R.drawable.button_circle);
                    buttonstandr.setBackgroundResource(R.drawable.button_circle);
                    buttonmodraty.setBackgroundResource(R.drawable.button_circle);
                    buttonvery.setBackgroundResource(R.drawable.button_circle);
                    res.setText(String.valueOf(result*1.9));
                    res.setText(String.valueOf(result1*1.9));

                } else if (isPressed == true) {
                    buttonexra.setBackgroundResource(R.drawable.button_circle);
                    textfifth.setVisibility(View.INVISIBLE);
                    isPressed = false;
                }
                break;


        }


    }

    public void Calculate(View view) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("user").child("healthinfo");
        if(gender.isEmpty() || gender.equals("null")){
            Toast.makeText(getApplicationContext(),"please choices your gender",Toast.LENGTH_LONG).show();
        }

        if(age ==0 || age <0 || Age.getText().toString().isEmpty() ){
            Toast.makeText(getApplicationContext(),"Age is Incorrect",Toast.LENGTH_SHORT).show();

        }
        if(entrheight ==0 || entrheight <0 ||wight.getText().toString().isEmpty() ){
            Toast.makeText(getApplicationContext(),"Height is Incorrect",Toast.LENGTH_SHORT).show();

        }
        if(entrwight ==0 || entrwight <0 || height.getText().toString().isEmpty() ){
            Toast.makeText(getApplicationContext(),"Wight is Incorrect",Toast.LENGTH_SHORT).show();

        }
        healthinfo = new profile();
healthinfo.setAge(String.valueOf( age));
healthinfo.setHeight(String.valueOf( entrheight));
healthinfo.setWight( String.valueOf(entrwight));
healthinfo.setGender(gender);
healthinfo.setAllrgy(item);
healthinfo.setCalure(res.getText().toString() );
myRef.push().setValue(healthinfo);
Toast.makeText(getApplicationContext(),"Insert successfully",Toast.LENGTH_LONG).show();

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        item = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(),"item selected is:"+item,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getApplicationContext(),"Nothing selected...",Toast.LENGTH_LONG).show();

    }
}