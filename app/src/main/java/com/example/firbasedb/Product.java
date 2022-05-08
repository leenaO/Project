package com.example.firbasedb;

public class Product {
    String image;
    String name;
    String section;
    String amount;
    String price;
    String ingredients;
    boolean keto=true;
    boolean sugarFree=true;
    boolean vegan=true;
    String productId;
    String productKey;

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Product(String image, String name, String section, String amount, String price, boolean keto, boolean sugarFree, boolean vegan, String productId, String productKey,String ingredients) {
        this.image = image;
        this.name = name;
        this.section = section;
        this.amount = amount;
        this.price = price;
        //this.totalPrice = totalPrice;
        this.keto = keto;
        this.sugarFree = sugarFree;
        this.vegan = vegan;
        this.productId = productId;
        this.productKey = productKey;
        this.ingredients=ingredients;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public boolean isSugarFree() {
        return sugarFree;
    }

    public void setSugarFree(boolean sugarFree) {
        this.sugarFree = sugarFree;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isKeto() {
        return keto;
    }

    public void setKeto(boolean keto) {
        this.keto = keto;
    }

    public Product(String image, String name, String section, String amount, String price, boolean keto,boolean sugarFree,boolean vegan) {
        this.image = image;
        this.name = name;
        this.section = section;
        this.amount = amount;
        this.price = price;

        this.keto = keto;
        this.sugarFree=sugarFree;
        this.vegan=vegan;
    }

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

//    public String getTotalPrice() {
//        return totalPrice;
//    }

//    public void setTotalPrice(String totalPrice) {
//        this.totalPrice = totalPrice;
//    }

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
