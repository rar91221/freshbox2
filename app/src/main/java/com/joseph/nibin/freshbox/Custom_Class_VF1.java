package com.joseph.nibin.freshbox;

/**
 * Created by Nibin on 3/1/2018.
 */

public class Custom_Class_VF1{

    private String image;
    private String product_name;
    private String unit;
    private String price;

    public String getImage()          { return image; }
    public String getProduct_name()   { return product_name; }
    public String getUnit()           { return unit; }
    public String getPrice()          { return price;}

    public Custom_Class_VF1(String image,String product_name,String unit,String price)
    {
        this.image=image;
        this.product_name=product_name;
        this.unit=unit;
        this.price=price;
    }
}

