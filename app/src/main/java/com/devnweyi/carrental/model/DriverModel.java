package com.devnweyi.carrental.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DriverModel implements Parcelable {

    int DriverID,Rating,ProductID;
    String DriverName,PhoneNumber,Age,CarNumber,PhotoUrl;

    public static final Creator<DriverModel> CREATOR = new Creator<DriverModel>() {
        public DriverModel createFromParcel(Parcel source) {
            return new DriverModel(source);
        }

        public DriverModel[] newArray(int size) {
            return new DriverModel[size];
        }
    };

    protected DriverModel(Parcel in){
        this.ProductID=in.readInt();
        this.DriverID=in.readInt();
        this.Rating=in.readInt();
        this.DriverName=in.readString();
        this.Age=in.readString();
        this.CarNumber=in.readString();
        this.PhoneNumber=in.readString();
        this.PhotoUrl=in.readString();
    }

    public int getDriverID() {
        return DriverID;
    }

    public void setDriverID(int driverID) {
        DriverID = driverID;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getCarNumber() {
        return CarNumber;
    }

    public void setCarNumber(String carNumber) {
        CarNumber = carNumber;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        PhotoUrl = photoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ProductID);
        dest.writeInt(this.DriverID);
        dest.writeInt(this.Rating);
        dest.writeString(this.DriverName);
        dest.writeString(this.Age);
        dest.writeString(this.CarNumber);
        dest.writeString(this.PhoneNumber);
        dest.writeString(this.PhotoUrl);
    }
}
