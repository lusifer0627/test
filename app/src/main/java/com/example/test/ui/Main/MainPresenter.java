package com.example.test.ui.Main;

import android.content.Context;
import android.util.Log;

import com.example.test.ui.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.presenter{
    private MainContract.view callBake;
    private Context context;
    public MainPresenter(MainContract.view view , Context context) {
        this.callBake = view;
        this.context = context;
    }
    @Override
    public void UserName(String token)
    {
        Api.api.getUserForm(token).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                Log.d("title", "請求成功");
                Log.d("title", response.body().name);
                callBake.usernameReturn(response.body().name);
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Log.d("title",t.toString());
            }
        });
    }
}
