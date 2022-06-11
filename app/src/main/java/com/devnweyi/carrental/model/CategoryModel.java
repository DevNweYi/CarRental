package com.devnweyi.carrental.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class CategoryModel {

    @Nullable
    @SerializedName("CategoryID")
    int categoryId;
    @SerializedName("CategoryName")
    String categoryName;
    boolean isSelected;
    byte[] CategoryPhoto;
    String CategoryPhotoUrl;

    @Nullable
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@Nullable int categoryId) {
        this.categoryId = categoryId;
    }

    @Nullable
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(@Nullable String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public byte[] getCategoryPhoto() {
        return CategoryPhoto;
    }

    public void setCategoryPhoto(byte[] categoryPhoto) {
        CategoryPhoto = categoryPhoto;
    }

    public String getCategoryPhotoUrl() {
        return CategoryPhotoUrl;
    }

    public void setCategoryPhotoUrl(String categoryPhotoUrl) {
        CategoryPhotoUrl = categoryPhotoUrl;
    }
}
