package com.devnweyi.carrental.model;

import androidx.annotation.Nullable;

public class BookingModel {

    @Nullable
    int ProductID,UserID,BookingID;
    String TripPlace,PickUpAddress,DropOffAddress,PickUpDate,PickUpTime,DropOffDate,DropOffTime,ProductName,PricePerDay,
            BookingDateTime,PickUpDay,DropOffDay,TotalAmount,TotalPassenger,RentalDays,ProductDescription,DetailPhotoUrl;

    public BookingModel(){

    }
    public BookingModel(BookingModel bookingModel){
        this.BookingID=bookingModel.BookingID;
        this.UserID=bookingModel.UserID;
        this.ProductID=bookingModel.ProductID;
        this.TripPlace=bookingModel.TripPlace;
        this.PickUpAddress=bookingModel.PickUpAddress;
        this.DropOffAddress=bookingModel.DropOffAddress;
        this.PickUpDate=bookingModel.PickUpDate;
        this.PickUpTime=bookingModel.PickUpTime;
        this.DropOffDate=bookingModel.DropOffDate;
        this.DropOffTime=bookingModel.DropOffTime;
        this.TotalPassenger=bookingModel.TotalPassenger;
        this.RentalDays=bookingModel.RentalDays;
        this.ProductName=bookingModel.ProductName;
        this.PricePerDay=bookingModel.PricePerDay;
        this.BookingDateTime=bookingModel.BookingDateTime;
        this.PickUpDay=bookingModel.PickUpDay;
        this.DropOffDay=bookingModel.DropOffDay;
        this.TotalAmount=bookingModel.TotalAmount;
        this.ProductDescription=bookingModel.ProductDescription;
        this.DetailPhotoUrl=bookingModel.DetailPhotoUrl;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int bookingID) {
        BookingID = bookingID;
    }

    public String getTripPlace() {
        return TripPlace;
    }

    public void setTripPlace(String tripPlace) {
        TripPlace = tripPlace;
    }

    public String getPickUpAddress() {
        return PickUpAddress;
    }

    public void setPickUpAddress(String pickUpAddress) {
        PickUpAddress = pickUpAddress;
    }

    public String getDropOffAddress() {
        return DropOffAddress;
    }

    public void setDropOffAddress(String dropOffAddress) {
        DropOffAddress = dropOffAddress;
    }

    public String getPickUpDate() {
        return PickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        PickUpDate = pickUpDate;
    }

    public String getPickUpTime() {
        return PickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        PickUpTime = pickUpTime;
    }

    public String getDropOffDate() {
        return DropOffDate;
    }

    public void setDropOffDate(String dropOffDate) {
        DropOffDate = dropOffDate;
    }

    public String getDropOffTime() {
        return DropOffTime;
    }

    public void setDropOffTime(String dropOffTime) {
        DropOffTime = dropOffTime;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getPricePerDay() {
        return PricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        PricePerDay = pricePerDay;
    }

    public String getBookingDateTime() {
        return BookingDateTime;
    }

    public void setBookingDateTime(String bookingDateTime) {
        BookingDateTime = bookingDateTime;
    }

    public String getPickUpDay() {
        return PickUpDay;
    }

    public void setPickUpDay(String pickUpDay) {
        PickUpDay = pickUpDay;
    }

    public String getDropOffDay() {
        return DropOffDay;
    }

    public void setDropOffDay(String dropOffDay) {
        DropOffDay = dropOffDay;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getTotalPassenger() {
        return TotalPassenger;
    }

    public void setTotalPassenger(String totalPassenger) {
        TotalPassenger = totalPassenger;
    }

    public String getRentalDays() {
        return RentalDays;
    }

    public void setRentalDays(String rentalDays) {
        RentalDays = rentalDays;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public String getDetailPhotoUrl() {
        return DetailPhotoUrl;
    }

    public void setDetailPhotoUrl(String detailPhotoUrl) {
        DetailPhotoUrl = detailPhotoUrl;
    }
}
