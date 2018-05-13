package com.example.gs.rentme.data_model;

import java.io.Serializable;

/**
 * Created by Mohit pal on 4/27/2018.
 */

public class Product_detail implements Serializable {


    public String name, loc, price, quantity, rentType,description , category , images , availability , email;

   public  Product_detail()
   {

   }

   public Product_detail(String name, String loc, String price, String quantity, String rentType, String description , String category , String availability)

   {

       this.name=name;
    this.loc=loc;
    this.description=description;
    this.price=price;
    this.quantity=quantity;
    this.rentType=rentType;
    this.category = category ;

    this.availability = availability;
}

}
