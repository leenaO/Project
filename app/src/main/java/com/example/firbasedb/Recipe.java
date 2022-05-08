package com.example.firbasedb;

import java.util.ArrayList;
import java.util.List;

public class Recipe  {
    private String img;
    private String recipeName;
    private String recipeMaker;
    private String howmake;
    private String dite;
    private List<String> ingrediant;
    private String like;
    private  String id;
    private  String recipeID;
    private String recipeKey;

    boolean selected = false;
    private String proudectkey;


    public String getProudectkey() {
        return proudectkey;
    }

    public void setProudectkey(String proudectkey) {
        this.proudectkey = proudectkey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipeKey() {
        return RecipeKey;
    }

    public void setRecipeKey(String recipeKey) {
        RecipeKey = recipeKey;
    }

    private String RecipeKey;


    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    private String image_uri;

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }



    private String recipeDiet;
    private String recipeProcedure;

    public Recipe()
    {}
    //    public Recipe(ArrayList<String> ingrediant)
//    {
//        this.ingrediant = ingrediant;
//    }
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

    public List<String> getIngrediant() {
        return ingrediant;
    }
    public void  setIngrediant(List<String> ingrediant) {

        this.ingrediant = ingrediant;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
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
