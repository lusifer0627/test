package com.example.test.ui.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.test.ui.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.presenter{
    private LoginContract.view callBake;
    private Context context;
    public LoginPresenter(LoginContract.view view , Context context) {
        this.callBake = view;
        this.context = context;
    }
    @Override
    public void LoginSent(String account, String password)
    {
        Api.api.getLoginData(account,password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("title", "請求成功");
                if(response.code() == 200) {
                    Log.d("title", response.body().token);
                    //利用SharedPreferences儲存token
                    SharedPreferences sharedPreferences = context.getSharedPreferences("Token", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    String dataToTransfer = response.body().token;
                    editor.putString("data_key", dataToTransfer).apply();
                    callBake.loginSuccess();
                }
                else {
                    callBake.loginError();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("title","請求失敗");
                callBake.loginApiError();
            }
        });
    }
}
