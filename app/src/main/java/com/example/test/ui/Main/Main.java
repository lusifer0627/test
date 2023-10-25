package com.example.test.ui.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.test.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Main extends AppCompatActivity implements MainContract.view {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private TextView tvUsername;
    private String[] tab_title = {"生產排程","當日進度表","訊息通知"};
    private String token="",username="";
    private MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findById();

        presenter = new MainPresenter(this , this);
        //取得token並取得使用者資料
        getToken();
        username();
    }
    @Override
    //取得名字的函式
    public void username() {
        presenter.UserName(token);
    }
    @Override
    //返回名字的函式
    public void usernameReturn(String name) {
        username=name;
        tvUsername.setText(name);
        startPage();
    }
    @Override
    //取得金鑰的函式
    public void getToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("Token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("data_key", "Value");
    }
    @Override
    //建立頁面的函式
    public void startPage(){
        viewPager2.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(),getLifecycle(),username,username));

        new TabLayoutMediator(tabLayout, viewPager2,true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                viewPager2.setCurrentItem(tab.getPosition());
            }
        }).attach();
        for(int i = 0 ; i < tabLayout.getTabCount() ; i++){
            tabLayout.getTabAt(i).setText(tab_title[i]);
        }
    }
    @Override
    //綁定元件的函式
    public void findById(){
        tabLayout=findViewById(R.id.tabLayout);
        viewPager2=findViewById(R.id.vp2);
        tvUsername=findViewById(R.id.textView);
    }
}