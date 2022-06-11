package com.devnweyi.carrental.model;

public class PriceFilterModel {

    int PriceFilterID;
    String PriceFilterValue,PriceFilterName;

    public int getPriceFilterID() {
        return PriceFilterID;
    }

    public void setPriceFilterID(int priceFilterID) {
        PriceFilterID = priceFilterID;
    }

    public String getPriceFilterValue() {
        return PriceFilterValue;
    }

    public void setPriceFilterValue(String priceFilterValue) {
        PriceFilterValue = priceFilterValue;
    }

    public String getPriceFilterName() {
        return PriceFilterName;
    }

    public void setPriceFilterName(String priceFilterName) {
        PriceFilterName = priceFilterName;
    }
}
