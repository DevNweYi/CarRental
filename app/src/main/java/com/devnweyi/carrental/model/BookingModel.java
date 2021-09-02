package com.devnweyi.carrental.model;

import androidx.annotation.Nullable;

public class BookingModel {

    @Nullable
    int productId,userId;
    String tripPlace,pickUpAddress,dropOffAddress,pickUpDate,pickUpTime,dropOffDate,dropOffTime,productName,PricePerDay,bookingDateTime,pickUpDay,dropOffDay,totalAmount,totalPassenger,rentalDays;

    public BookingModel(){

    }
    public BookingModel(BookingModel bookingModel){
        this.userId=bookingModel.userId;
        this.productId=bookingModel.productId;
        this.tripPlace=bookingModel.tripPlace;
        this.pickUpAddress=bookingModel.pickUpAddress;
        this.dropOffAddress=bookingModel.dropOffAddress;
        this.pickUpDate=bookingModel.pickUpDate;
        this.pickUpTime=bookingModel.pickUpTime;
        this.dropOffDate=bookingModel.dropOffDate;
        this.dropOffTime=bookingModel.dropOffTime;
        this.totalPassenger=bookingModel.totalPassenger;
        this.rentalDays=bookingModel.rentalDays;
        this.productName=bookingModel.productName;
        this.PricePerDay=bookingModel.PricePerDay;
        this.bookingDateTime=bookingModel.bookingDateTime;
        this.pickUpDay=bookingModel.pickUpDay;
        this.dropOffDay=bookingModel.dropOffDay;
        this.totalAmount=bookingModel.totalAmount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Nullable
    public String getTripPlace() {
        return tripPlace;
    }

    public void setTripPlace(@Nullable String tripPlace) {
        this.tripPlace = tripPlace;
    }

    @Nullable
    public String getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(@Nullable String pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    @Nullable
    public String getDropOffAddress() {
        return dropOffAddress;
    }

    public void setDropOffAddress(@Nullable String dropOffAddress) {
        this.dropOffAddress = dropOffAddress;
    }

    @Nullable
    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(@Nullable String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    @Nullable
    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(@Nullable String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    @Nullable
    public String getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(@Nullable String dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    @Nullable
    public String getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(@Nullable String dropOffTime) {
        this.dropOffTime = dropOffTime;
    }

    public String getTotalPassenger() {
        return totalPassenger;
    }

    public void setTotalPassenger(String totalPassenger) {
        this.totalPassenger = totalPassenger;
    }

    public String getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(String rentalDays) {
        this.rentalDays = rentalDays;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPricePerDay() {
        return PricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        PricePerDay = pricePerDay;
    }

    public String getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(String bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public String getPickUpDay() {
        return pickUpDay;
    }

    public void setPickUpDay(String pickUpDay) {
        this.pickUpDay = pickUpDay;
    }

    public String getDropOffDay() {
        return dropOffDay;
    }

    public void setDropOffDay(String dropOffDay) {
        this.dropOffDay = dropOffDay;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
