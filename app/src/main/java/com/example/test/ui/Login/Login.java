package com.example.test.ui.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.ui.Main.Main;
import com.example.test.R;

public class Login extends AppCompatActivity implements LoginContract.view {
    private Button btnLogin;
    private EditText account,password;
    private Context context=this;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        findById();

        presenter = new LoginPresenter(this , this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginSent();
            }
        });
    }
    @Override
    //送出帳號密碼資料
    public void loginSent()
    {
        presenter.LoginSent(account.getText().toString(),password.getText().toString());
    }
    @Override
    //帳密錯誤時的返回函式
    public void loginError()
    {
        Toast.makeText(context,"帳密錯誤",Toast.LENGTH_LONG).show();
    }
    @Override
    public void loginApiError(){
        Toast.makeText(context,"連線失敗，請檢查網路連接是否正常",Toast.LENGTH_LONG).show();
    }
    @Override
    //帳密正確時的返回函式
    public void loginSuccess()
    {
        Toast.makeText(context,"歡迎登入",Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setClass(Login.this, Main.class);
        startActivity(intent);
    }
    @Override
    //綁定元件的函式
    public void findById()
    {
        account=findViewById(R.id.editTextTextPersonName2);
        password=findViewById(R.id.editTextTextPassword2);
        btnLogin = findViewById(R.id.button);
    }
}



