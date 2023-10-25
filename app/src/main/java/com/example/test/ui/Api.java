package com.example.test.ui;

import com.example.test.ui.DaySch.DayForm.DayFormFrontResponse;
import com.example.test.ui.DaySch.DayForm.DayFormOrderResponse;
import com.example.test.ui.Login.LoginResponse;
import com.example.test.ui.Main.MainResponse;
import com.example.test.ui.Schedule.Search.SearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.20.1.2:8089/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Api api = retrofit.create(Api.class);

    //Login===============================================
    @POST("api/auth/login")
    Call<LoginResponse> getLoginData(
            @Query("account") String account,
            @Query("password") String password);

    //Main================================================
    @GET("api/auth")
    Call<MainResponse> getUserForm(
            @Query("token") String token);

    //Search==============================================
    @GET("api/app-search-customer")
    Call<List<SearchResponse>> getSearchCustomer(
            @Query("customer_name") String customer_name,
            @Query("token") String token);
    @GET("api/app-search-so")
    Call<List<SearchResponse>> getSearchSo(
            @Query("so_id") String so_id,
            @Query("token") String token);
    @GET("api/get-manufacture")
    Call<List<SearchResponse>> getSearch(
            @Query("online_date") String date,
            @Query("sale_order") String customer_so,
            @Query("customer") String customer_name,
            @Query("token") String token);

    //DayForm=============================================
    @GET("api/get-prev-manufacture")
    Call<List<DayFormFrontResponse>> getFront(
            @Query("so_id") String so_id,
            @Query("item_id") String item_id,
            @Query("token") String token);
    @GET("api/get-sale-order")
    Call<List<DayFormOrderResponse>> getOrder(
            @Query("sale_order") String sale_order,
            @Query("customer") String customer,
            @Query("online_date") String online_date,
            @Query("token") String token);
}
