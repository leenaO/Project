package com.example.firbasedb;

import java.security.PrivateKey;
import java.util.List;

public class Recipe  {
    private String img;
    private String recipeName;
    private String recipeMaker;
    private String dite;
    private String howmake;
    private String recipeKey;
    private String rcipeID;
    private String id;
    private String deletID;

    public String getHowmake() {
        return howmake;
    }

    public void setHowmake(String howmake) {
        this.howmake = howmake;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getDeletID() {
        return deletID;
    }

    public void setDeletID(String deletID) {
        this.deletID = deletID;
    }

    public String getDite() {
        return dite;
    }

    public void setDite(String dite) {
        this.dite = dite;
    }

    public String getRcipeID() {
        return rcipeID;
    }

    public void setRcipeID(String rcipeID) {
        this.rcipeID = rcipeID;
    }

    public Recipe()
    {}


    public String getRecipeKey() {
        return recipeKey;
    }

    public void setRecipeKey(String recipeKey) {
        this.recipeKey = recipeKey;
    }



    public void setImg(String img) {
        this.img = img;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setRecipeMaker(String recipeMaker) {
        this.recipeMaker = recipeMaker;
    }




    public String getImg() {
        return img;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeMaker() {
        return recipeMaker;
    }

}
