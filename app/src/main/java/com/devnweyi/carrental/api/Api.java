package com.devnweyi.carrental.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit=null;

    public static ApiInterface getClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl("http://192.168.43.195/RentalWebService/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        ApiInterface api=retrofit.create(ApiInterface.class);
        return api;
    }
}