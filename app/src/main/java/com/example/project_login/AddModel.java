package com.example.project_login;

import android.content.Intent;
import android.graphics.Bitmap;

public class AddModel {
    private  int id;
    //private static Bitmap image ;
    private  String Title ;
    private  String desc ;
    private  int price ;
    private  String size ;

    public AddModel( int id , String title, String desc, int price, String size) {
        //this.image = image ;
        this.id = id ;
        this.Title = title;
        this.desc = desc ;
        this.price = price;
        this.size = size;
    }

    /*public static Bitmap getImage() {
        return image;
    }

    public static void setImage(Bitmap image) {
        AddModel.image = image;
    }*/

    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public  String getDesc() {
        return desc;
    }


    public  int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public  String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
       /* return "ID='" + id + '\'' +
                "Title='" + Title + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", size='" + size + '\'' ;

        */
        return
                "desc: " + desc  ;
    }

}
