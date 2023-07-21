package com.example.lab34.api;

import com.example.lab34.model.DataMong;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface Apiservide {
    Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();

    Apiservide apiservice = new Retrofit.Builder()
            .baseUrl("http://192.168.1.6:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Apiservide.class);

    @GET("danhsach/danhsach")
    Call<List<DataMong>> getdata();
}
