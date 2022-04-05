package com.example.firbasedb;


public class Recipe  {
    private String img;
    private String recipeName;
    private String recipeMaker;
    private String recipeDiet;
    private String recipeProcedure;

    public Recipe()
    {}
    public Recipe(String img, String recipeName, String recipeMaker) {
        this.img = img;
        this.recipeName = recipeName;
        this.recipeMaker=recipeMaker;
    }

    public Recipe(String img, String recipeName, String recipeMaker, String recipeDiet, String recipeProcedure) {
        this.img = img;
        this.recipeName = recipeName;
        this.recipeMaker = recipeMaker;
        this.recipeDiet = recipeDiet;
        this.recipeProcedure = recipeProcedure;
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

    public void setRecipeDiet(String recipeDiet) {
        this.recipeDiet = recipeDiet;
    }

    public String getRecipeDiet() {
        return recipeDiet;
    }

    public String getRecipeProcedure() {
        return recipeProcedure;
    }

    public void setRecipeProcedure(String recipeProcedure) {
        this.recipeProcedure = recipeProcedure;
    }
}
