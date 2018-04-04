package com.example.shakt.baked;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhildhirmalani on 12/03/18.
 */

public class Product_Class {

    private List<Integer> effects;
    private List<Integer> negative;
    private List<Integer> medical;
    private String description;
    private String type;
    private List<String> flavour;
    private String productPic;
    private String productInfo;
    private List<String> imageUrl;
    private List<String> webUrl;
    private List<Integer>thcCbd;
    private List<String>reviews;
    private List<Integer>rating;
    private List<String>user;


    public Product_Class() {
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public List<Integer> getRating() {
        return rating;
    }

    public void setRating(List<Integer> rating) {
        this.rating = rating;
    }

    public List<String> getUser() {
        return user;
    }

    public void setUser(List<String> user) {
        this.user = user;
    }

    public List<Integer> getThcCbd() {
        return thcCbd;
    }

    public void setThcCbd(List<Integer> thcCbd) {
        thcCbd = thcCbd;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public List<String> getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(List<String> webUrl) {
        this.webUrl = webUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        imageUrl = imageUrl;
    }

    public List<Integer> getEffects() {
        return effects;
    }

    public void setEffects(List<Integer> effects) {
        effects = effects;
    }

    public List<Integer> getNegative() {
        return negative;
    }

    public void setNegative(List<Integer> negative) {
        negative = negative;
    }

    public List<Integer> getMedical() {
        return medical;
    }

    public void setMedical(List<Integer> medical) {
        medical = medical;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type= type;
    }

    public List<String> getFlavour() {
        return flavour;
    }

    public void setFlavour(List<String> flavour) {
        this.flavour = flavour;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }
}
