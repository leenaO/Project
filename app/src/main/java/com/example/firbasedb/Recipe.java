package com.example.firbasedb;

public class Recipe {
    private String img;
    private String recipeName;
    private String recipeMaker;
    public Recipe()
    {}
    public Recipe(String img, String recipeName, String recipeMaker) {
        this.img = img;
        this.recipeName = recipeName;
        this.recipeMaker=recipeMaker;
    }

    public String getImg() {
        return img;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeMaker() {
        return recipeMaker;
    }

    public void setRecipeMaker(String recipeMaker) {
        this.recipeMaker = recipeMaker;
    }
}
