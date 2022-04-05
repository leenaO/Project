package com.example.firbasedb;

import java.security.PrivateKey;
import java.util.List;

public class Recipe  {
    private String img;
    private String recipeName;
    private String recipeMaker;
    private String howmake;
    private String dite;
    private String ingrediant;
    private String like;
    private  String ID;
    private  String time;
    private String deletID;

    public String getDeletID() {
        return deletID;
    }

    public void setDeletID(String deletID) {
        this.deletID = deletID;
    }

    public String getRcipeID() {
        return rcipeID;
    }

    public void setRcipeID(String rcipeID) {
        this.rcipeID = rcipeID;
    }

    private String rcipeID;


    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getTime() {
        return time;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

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
    public String getHowmake() {
        return howmake;
    }

    public void setHowmake(String howmake) {
        this.howmake = howmake;
    }

    public String getDite() {
        return dite;
    }

    public void setDite(String dite) {
        this.dite = dite;
    }

    public String getIngrediant() {
        return ingrediant;
    }
    public void setIngrediant(String ingrediant) {
        this.ingrediant = ingrediant;
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
