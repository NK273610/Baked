package com.example.shakt.baked;

import java.util.ArrayList;

/**
 * Created by nikhildhirmalani on 12/03/18.
 */

public class Product_Class {

    ArrayList<Integer> effects_list;
    ArrayList<Integer> negative_list;
    ArrayList<Integer> medical_list;
    String description;
    ArrayList<String> flavour_list;
    String pic_url;

    public Product_Class() {
    }

    public ArrayList<Integer> getEffects_list() {
        return effects_list;
    }

    public void setEffects_list(ArrayList<Integer> effects_list) {
        this.effects_list = effects_list;
    }

    public ArrayList<Integer> getNegative_list() {
        return negative_list;
    }

    public void setNegative_list(ArrayList<Integer> negative_list) {
        this.negative_list = negative_list;
    }

    public ArrayList<Integer> getMedical_list() {
        return medical_list;
    }

    public void setMedical_list(ArrayList<Integer> medical_list) {
        this.medical_list = medical_list;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getFlavour_list() {
        return flavour_list;
    }

    public void setFlavour_list(ArrayList<String> flavour_list) {
        this.flavour_list = flavour_list;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
