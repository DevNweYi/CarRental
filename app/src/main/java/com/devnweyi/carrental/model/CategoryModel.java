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
}
