package com.example.firbasedb;

import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

public class profile  {

   private String name;
   private String email;
   private Integer phonNo;
   private String pass;
   private String avatar;

private String age ;
private String height;
   private String wight;
   private String activate;
   private String gender;
   private String calure;

   public String getAllrgy() {
      return allrgy;
   }

   public void setAllrgy(String allrgy) {
      this.allrgy = allrgy;
   }

   private  String allrgy;


   public String getHeight() {
      return height;
   }

   public void setHeight(String height) {
      this.height = height;
   }

   public String getWight() {
      return wight;
   }

   public void setWight(String wight) {
      this.wight = wight;
   }

   public String getActivate() {
      return activate;
   }

   public void setActivate(String activate) {
      this.activate = activate;
   }

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public String getCalure() {
      return calure;
   }

   public void setCalure(String calure) {
      this.calure = calure;
   }



   public String getAge() {
      return age;
   }

   public void setAge(String age) {
      this.age = age;
   }


   public String getPass() {
      return pass;
   }

   public void setPass(String pass) {
      this.pass = pass;
   }

   public profile() {
   }



   public String getName() {
      return name;
   }

   public String getEmail() {
      return email;
   }

   public Integer getPhonNo() {
      return phonNo;
   }

   public String getAvatar() {
      return avatar;
   }



   public void setName(String name) {
      this.name = name;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setPhonNo(Integer phonNo) {
      this.phonNo = phonNo;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }

   public profile(String name, String email, Integer phonNo,String pass) {
      this.name = name;
      this.email = email;
      this.phonNo = phonNo;
        this.pass = pass;
   }



}

