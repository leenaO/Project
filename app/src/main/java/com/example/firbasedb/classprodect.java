package com.example.firbasedb;

class classprodect {
   String nameitem, pricitem, uriitem,quanteitem,totlepriceitem;

   public classprodect() {
   }
   public classprodect(String nameitem, String pricitem, String uriitem, String quanteitem, String totlepriceitem) {
      this.nameitem = nameitem;
      this.pricitem = pricitem;
      this.uriitem = uriitem;
      this.quanteitem = quanteitem;
      this.totlepriceitem = totlepriceitem;
   }
   public String getNameitem() {
      return nameitem;
   }


   public void setNameitem(String nameitem) {
      this.nameitem = nameitem;
   }

   public String getPricitem() {
      return pricitem;
   }

   public void setPricitem(String pricitem) {
      this.pricitem = pricitem;
   }

   public String getUriitem() {
      return uriitem;
   }

   public void setUriitem(String uriitem) {
      this.uriitem = uriitem;
   }

   public String getQuanteitem() {
      return quanteitem;
   }

   public void setQuanteitem(String quanteitem) {
      this.quanteitem = quanteitem;
   }

   public String getTotlepriceitem() {
      return totlepriceitem;
   }

   public void setTotlepriceitem(String totlepriceitem) {
      this.totlepriceitem = totlepriceitem;
   }
}
