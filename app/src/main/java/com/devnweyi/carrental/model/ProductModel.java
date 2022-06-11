package com.devnweyi.carrental.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

public class ProductModel implements Parcelable {

    @Nullable
    int ProductID,CategoryID,TotalWindow,AllowPassengerNo,AllowPassengerBag,TotalSeat;
    String ProductName,ProductDescription,PricePerDay,SpeedPerHour,GearSystem,Cylinder,FuelType,SmallPhotoUrl,DetailPhotoUrl;
    boolean IsAircon;

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public int getTotalWindow() {
        return TotalWindow;
    }

    public void setTotalWindow(int totalWindow) {
        TotalWindow = totalWindow;
    }

    public int getAllowPassengerNo() {
        return AllowPassengerNo;
    }

    public void setAllowPassengerNo(int allowPassengerNo) {
        AllowPassengerNo = allowPassengerNo;
    }

    public int getAllowPassengerBag() {
        return AllowPassengerBag;
    }

    public void setAllowPassengerBag(int allowPassengerBag) {
        AllowPassengerBag = allowPassengerBag;
    }

    public int getTotalSeat() {
        return TotalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        TotalSeat = totalSeat;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public String getPricePerDay() {
        return PricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        PricePerDay = pricePerDay;
    }

    public String getSpeedPerHour() {
        return SpeedPerHour;
    }

    public void setSpeedPerHour(String speedPerHour) {
        SpeedPerHour = speedPerHour;
    }

    public String getGearSystem() {
        return GearSystem;
    }

    public void setGearSystem(String gearSystem) {
        GearSystem = gearSystem;
    }

    public String getCylinder() {
        return Cylinder;
    }

    public void setCylinder(String cylinder) {
        Cylinder = cylinder;
    }

    public String getFuelType() {
        return FuelType;
    }

    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }

    public boolean isAircon() {
        return IsAircon;
    }

    public void setAircon(boolean aircon) {
        IsAircon = aircon;
    }

    public String getSmallPhotoUrl() {
        return SmallPhotoUrl;
    }

    public void setSmallPhotoUrl(String smallPhotoUrl) {
        SmallPhotoUrl = smallPhotoUrl;
    }

    public String getDetailPhotoUrl() {
        return DetailPhotoUrl;
    }

    public void setDetailPhotoUrl(String detailPhotoUrl) {
        this.DetailPhotoUrl = detailPhotoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ProductID);
        dest.writeInt(this.CategoryID);
        dest.writeInt(this.TotalWindow);
        dest.writeInt(this.AllowPassengerNo);
        dest.writeInt(this.AllowPassengerBag);
        dest.writeInt(this.TotalSeat);
        dest.writeString(this.ProductName);
        dest.writeString(this.ProductDescription);
        dest.writeString(this.PricePerDay);
        dest.writeString(this.SpeedPerHour);
        dest.writeString(this.GearSystem);
        dest.writeString(this.Cylinder);
        dest.writeString(this.FuelType);
        dest.writeString(this.DetailPhotoUrl);
    }

    protected ProductModel(Parcel in){
        this.ProductID=in.readInt();
        this.CategoryID=in.readInt();
        this.TotalWindow=in.readInt();
        this.AllowPassengerNo=in.readInt();
        this.AllowPassengerBag=in.readInt();
        this.TotalSeat=in.readInt();
        this.ProductName=in.readString();
        this.ProductDescription=in.readString();
        this.PricePerDay=in.readString();
        this.SpeedPerHour=in.readString();
        this.GearSystem=in.readString();
        this.Cylinder=in.readString();
        this.FuelType=in.readString();
        this.DetailPhotoUrl=in.readString();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        public ProductModel createFromParcel(Parcel source) {
            return new ProductModel(source);
        }

        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

}
