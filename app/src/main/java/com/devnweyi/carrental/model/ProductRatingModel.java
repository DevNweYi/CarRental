package com.devnweyi.carrental.model;

import androidx.annotation.Nullable;

public class ProductRatingModel {

    @Nullable
    int RatingID,ProductID,TypeID,Rating;
    String Type;

    public int getRatingID() {
        return RatingID;
    }

    public void setRatingID(int ratingID) {
        RatingID = ratingID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
