package com.example.shakt.baked;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhildhirmalani on 12/03/18.
 */

public class Product_Class {

    private List<Integer> Effects;
    private List<Integer> Negative;
    private List<Integer> Medical;
    private String description;
    private List<String> flavour;
    private String product_pic;
    private String product_info;
    private List<String> Image_Url;
    private List<String> web_url;
    private List<Integer>ThcCbd;


    public Product_Class(     List<Integer> Effects ,
             List<Integer> Negative,
             List<Integer> Medical,
             String description,
             List<String> flavour,
             String product_pic,
             String product_info,
            List<String> Image_Url) {

        this.Effects = Effects ;
        this.Negative = Negative ;
        this.Medical = Medical ;
        this.description = description ;
        this.flavour = flavour ;
        this.product_pic = product_pic ;
        this.product_info = product_info ;
    }

    public Product_Class(){} ;

    public List<Integer> getThcCbd() {
        return ThcCbd;
    }

    public void setThcCbd(List<Integer> thcCbd) {
        ThcCbd = thcCbd;
    }

    public List<String> getImage_Url() {
        return Image_Url;
    }

    public List<String> getWeb_url() {
        return web_url;
    }

    public void setWeb_url(List<String> web_url) {
        this.web_url = web_url;
    }

    public void setImage_Url(List<String> image_Url) {
        Image_Url = image_Url;
    }

    public List<Integer> getEffects() {
        return Effects;
    }

    public void setEffects(List<Integer> Effects) {
        Effects = Effects;
    }

    public List<Integer> getNegative() {
        return Negative;
    }

    public void setNegative(List<Integer> Negative) {
        Negative = Negative;
    }

    public List<Integer> getMedical() {
        return Medical;
    }

    public void setMedical(List<Integer> Medical) {
        Medical = Medical;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public List<String> getflavour() {
        return flavour;
    }

    public void setflavour(List<String> flavour) {
        this.flavour = flavour;
    }

    public String getproduct_pic() {
        return product_pic;
    }

    public void setproduct_pic(String product_pic) {
        this.product_pic = product_pic;
    }

    public String getproduct_info() {
        return product_info;
    }

    public void setproduct_info(String product_info) {
        this.product_info = product_info;
    }
}
