package com.example.firbasedb;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class
Warn extends AppCompatActivity {
    Dialog mydialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warn);
        mydialog = new Dialog(this);}

    public void ShowPopup(View v){
        TextView txtclose;
        Button btn;
        mydialog.setContentView(R.layout.activity_warn);
        txtclose=(TextView)  mydialog.findViewById(R.id.txtclose);
        btn = (Button) mydialog.findViewById(R.id.SeeSug);
        txtclose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mydialog.dismiss();
            }
        });
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mydialog.show();
    }
}
