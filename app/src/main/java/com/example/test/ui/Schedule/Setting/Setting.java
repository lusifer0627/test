package com.example.test.ui.Schedule.Setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.R;

public class Setting extends AppCompatActivity {
    private ImageView back;
    private TextView tv_username;
    private String username="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        findById();

        back();

        getName();
    }

    //綁定元件的函式
    public void findById(){
        tv_username=findViewById(R.id.textView9);
        back=findViewById(R.id.imageView9);
    }

    //返回上頁的函式
    public void back(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //取得名字的函式
    public void getName(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            username = bundle.getString("usernameKey_setting");
            tv_username.setText(username);
        }
    }
}