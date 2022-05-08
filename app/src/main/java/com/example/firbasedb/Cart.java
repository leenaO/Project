package com.example.firbasedb;

import android.os.Bundle;
import android.view.GestureDetector;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cart{
    private String img;
    private String name;
    private String price;



    private String productId;
    static double totalPrice;

    private String productAmountInCart;
    private String amount;

    public String getAmount() {
        return amount;
    }



    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductAmountInCart() {
        return productAmountInCart;
    }

    public void setProductAmountInCart(String productAmountInCart) {
        this.productAmountInCart = productAmountInCart;
    }
}
