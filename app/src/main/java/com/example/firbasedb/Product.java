package com.example.firbasedb;

public class Product {
    String image;
    String name;
    String section;
    String amount;
    String price;

    public Product() {

    }

    public Product(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public Product(String image, String name, String section, String amount, String price) {
        this.image = image;
        this.name = name;
        this.section = section;
        this.amount = amount;
        this.price = price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public String getSection() {
        return section;
    }

    public String getAmount() {
        return amount;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
