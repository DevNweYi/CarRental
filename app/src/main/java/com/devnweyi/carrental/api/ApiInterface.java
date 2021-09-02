package com.devnweyi.carrental.api;

import com.devnweyi.carrental.model.BookingModel;
import com.devnweyi.carrental.model.CategoryModel;
import com.devnweyi.carrental.model.ProductModel;
import com.devnweyi.carrental.model.ProductRatingModel;
import com.devnweyi.carrental.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
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

    @GET("api/productrating")  // API's endpoint
    Call<List<ProductRatingModel>> getProductRating(@Query("productId") int productId);

    @Headers("Content-type: application/json")
    @POST("api/booking")
    Call<POST> sendBooking(@Body BookingModel bookingModel);

    @Headers("Content-type: application/json")
    @POST("api/user")
    Call<List<UserModel>> sendUser(@Body UserModel userModel);

    @GET("api/user")  // API's endpoint
    Call<List<UserModel>> signInUser(@Query("phoneNumber") String phoneNumber,@Query("password") String password);

    @GET("api/user")  // API's endpoint
    Call<Void> forgotPassword(@Query("phoneNumber") String phoneNumber);
}
