package com.devnweyi.carrental.api;

import com.devnweyi.carrental.model.BookingModel;
import com.devnweyi.carrental.model.CategoryModel;
import com.devnweyi.carrental.model.DriverModel;
import com.devnweyi.carrental.model.PassengerFilterModel;
import com.devnweyi.carrental.model.PriceFilterModel;
import com.devnweyi.carrental.model.ProductModel;
import com.devnweyi.carrental.model.ProductRatingModel;
import com.devnweyi.carrental.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/category")  // API's endpoint
    Call<List<CategoryModel>> getCategory();

    @GET("api/product")  // API's endpoint
    Call<List<ProductModel>> getProduct();

    @GET("api/product")  // API's endpoint
    Call<List<ProductModel>> getProduct(@Query("categoryId") int categoryId);

    @GET("api/product")  // API's endpoint
    Call<List<ProductModel>> getProduct(@Query("productName") String productName);

    @GET("api/product")  // API's endpoint
    Call<List<ProductModel>> getProduct(@Query("passengerFilterValue") int passengerFilterValue,@Query("priceFilterValue") String priceFilterValue);

    @GET("api/productrating")  // API's endpoint
    Call<List<ProductRatingModel>> getProductRating(@Query("productId") int productId);

    @Headers("Content-type: application/json")
    @POST("api/booking")
    Call<Void> sendBooking(@Body BookingModel bookingModel);

    @Headers("Content-type: application/json")
    @POST("api/user/AddUser")
    Call<List<UserModel>> sendUser(@Body UserModel userModel);

    @Headers("Content-type: application/json")
    @POST("api/user/EditUser")
    Call<Void> editUser(@Body UserModel userModel);

    @GET("api/user")  // API's endpoint
    Call<List<UserModel>> signInUser(@Query("phoneNumber") String phoneNumber,@Query("password") String password);

    @GET("api/user")  // API's endpoint
    Call<Void> forgotPassword(@Query("phoneNumber") String phoneNumber);

    @GET("api/driver")  // API's endpoint
    Call<List<DriverModel>> getDriver(@Query("productId") int productId);

    @GET("api/booking")  // API's endpoint
    Call<List<BookingModel>> getBooking(@Query("userId") int userId);

    @DELETE("api/booking")
    Call<Void> deleteBooking(@Query("userId") int userId);

    @GET("api/pricefilter")
    Call<List<PriceFilterModel>> getPriceFilter();

    @GET("api/passengerfilter")
    Call<List<PassengerFilterModel>> getPassengerFilter();
}
