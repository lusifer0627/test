package com.example.test.ui.DaySch.DayForm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class DayForm extends AppCompatActivity implements DayFormContract.view{
    private TabLayout tabLayout3;
    private ViewPager2 viewPager3;
    private String token="",username="";
    private DayFormPresenter presenter;
    private String[] tab_title1 = {"前關製令","本階製令","後關製令","裝配製令","銷售訂單"};
    private String[] dayForm1 = {"","","","","預計上線：","生產數量：","","","計劃開始：","計劃結束：","","","",""};
    private String[] dayForm2 = {"","","","","預計上線：","生產數量：","","","計劃開始：","計劃結束：","","","",""};
    private String[] dayForm3 = {"","","","","預計上線：","生產數量：","","","計劃開始：","計劃結束：","","","",""};
    private String[] dayForm4 = {"","","","","預計上線：","生產數量：","","","計劃開始：","計劃結束：","","","",""};
    private String[] dayForm5 = {"","","","","客戶名稱：","客戶訂單：","","","業務人員:","","","","",""};
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tvTitle,tv_username;
    private ImageView imgBack,imgLeft,imgRight;
    private ArrayList<String> onceData = new ArrayList<>();
    private ArrayList<String> formFront = new ArrayList<>();
    private ArrayList<String> formFrontList = new ArrayList<>();
    private ArrayList<String> formOrder = new ArrayList<>();
    private ArrayList<String> formOrderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_form);

        findById();

        presenter = new DayFormPresenter(this);

        back();

        //取得金鑰
        getToken();

        //將資料從上一個頁面接收
        Bundle extraData = getIntent().getExtras();
        if (extraData != null) {
            //如果資料是從當日進度表進入，會傳一個string
            if (extraData.containsKey("usernameKeyNoApi")) {
                Object data = extraData.get("usernameKeyNoApi");

                if(data instanceof String)
                {
                    tv_username.setText((String)data);

                    //初始化
                    for(int i=0;i<dayForm1.length;i++) {
                        formFront.add(dayForm1[i]);
                        formOrder.add(dayForm5[i]);
                    }
                    for(int i=0;i<11;i++) {
                        formFrontList.add("");
                    }
                    for(int i=0;i<10;i++) {
                        formOrderList.add("");
                    }
                    startPage();
                }
            }
            //如果資料是從進度表查詢進入，會傳一個arraylist
            else{
                tvTitle.setText("進度表查詢");
                onceData = extraData.getStringArrayList("onceData");
                //取得名字，將名字印出
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    username = bundle.getString("usernameKey_api");
                    tv_username.setText(username);
                }
                //先執行抓取所有資料，最後創建頁面架構
                searchFront();
                searchOrder();
                searchFrontList();
                searchOrderList();
            }
        }
    }
    @Override
    //取得前關製令標頭資料的函式
    public void searchFront() {
        presenter.SearchFront(onceData.get(0), onceData.get(1), token);
    }
    @Override
    //取得銷售訂單標頭資料的函式
    public void searchOrder() {
        presenter.SearchOrder(onceData.get(0), onceData.get(2),onceData.get(3), token);
    }
    @Override
    //取得前關製令內容資料的函式
    public void searchFrontList() {
        presenter.SearchFrontList(onceData.get(0), onceData.get(1), token);
    }
    @Override
    //取得銷售訂單內容資料的函式
    public void searchOrderList() {
        presenter.SearchOrderList(onceData.get(0), onceData.get(2),onceData.get(3), token);
    }
    @Override
    //返回前關製令標頭資料的函式
    public void searchFrontReturn(ArrayList<String> arrayList) {
        for(int i=0;i<arrayList.size();i++)
        {
            formFront.add(arrayList.get(i));
        }
    }
    @Override
    //返回銷售訂單標頭資料的函式
    public void searchOrderReturn(ArrayList<String> arrayList5) {
        for(int i=0;i<arrayList5.size();i++)
        {
            formOrder.add(arrayList5.get(i));
        }
    }
    @Override
    //返回前關製令內容資料的函式
    public void searchFrontListReturn(ArrayList<String> arrayList_front) {
        for(int i=0;i<arrayList_front.size();i++)
        {
            formFrontList.add(arrayList_front.get(i));
        }
    }
    @Override
    //返回銷售訂單內容資料的函式
    public void searchOrderListReturn(ArrayList<String> arrayList_order) {
        for(int i=0;i<arrayList_order.size();i++)
        {
            formOrderList.add(arrayList_order.get(i));
        }
        //開始生成五個頁面
        startPage();
    }

    //抓取金鑰的函式
    public void getToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("Token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("data_key", "Value");
    }

    public void findById(){
        imgBack=findViewById(R.id.imageView14);
        imgLeft=findViewById(R.id.imageView25);
        imgRight=findViewById(R.id.imageView26);
        tv1=findViewById(R.id.textView32);
        tv2=findViewById(R.id.textView33);
        tv3=findViewById(R.id.textView26);
        tv4=findViewById(R.id.textView31);
        tv5=findViewById(R.id.textView27);
        tv6=findViewById(R.id.textView34);
        tv7=findViewById(R.id.textView28);
        tv8=findViewById(R.id.textView35);
        tv9=findViewById(R.id.textView29);
        tv10=findViewById(R.id.textView36);
        tv11=findViewById(R.id.textView30);
        tv12=findViewById(R.id.textView37);
        tv13=findViewById(R.id.textView40);
        tv14=findViewById(R.id.textView42);
        tv_username=findViewById(R.id.textView25);
        tvTitle=findViewById(R.id.textView24);
        tabLayout3=findViewById(R.id.tabLayout3);
        viewPager3=findViewById(R.id.vp3);
    }

    public void back(){
        //返回功能
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //印出標頭資訊的函式
    public void textviewForm(int i){
        switch (i){
            case 0:
                tv1.setText(formFront.get(0));tv1.setTextColor(Color.parseColor("#FF009900"));
                tv2.setText(formFront.get(1));
                tv3.setText(formFront.get(2));tv3.setTextColor(Color.parseColor("#FF009900"));
                tv4.setText(formFront.get(3));
                tv5.setText(formFront.get(4));
                tv6.setText(formFront.get(5));
                tv7.setText(formFront.get(6));
                tv8.setText(formFront.get(7));
                tv9.setText(formFront.get(8));
                tv10.setText(formFront.get(9));
                tv11.setText(formFront.get(10));
                tv12.setText(formFront.get(11));
                tv13.setText(formFront.get(12));tv13.setTextColor(Color.parseColor("#FF009900"));
                tv14.setText(formFront.get(13));tv14.setTextColor(Color.parseColor("#FFCC0000"));
                break;
            case 1:
                tv1.setText(dayForm2[0]);tv1.setTextColor(Color.parseColor("#FF009900"));
                tv2.setText(dayForm2[1]);
                tv3.setText(dayForm2[2]);tv3.setTextColor(Color.parseColor("#FF009900"));
                tv4.setText(dayForm2[3]);
                tv5.setText(dayForm2[4]);
                tv6.setText(dayForm2[5]);
                tv7.setText(dayForm2[6]);
                tv8.setText(dayForm2[7]);
                tv9.setText(dayForm2[8]);
                tv10.setText(dayForm2[9]);
                tv11.setText(dayForm2[10]);
                tv12.setText(dayForm2[11]);
                tv13.setText(dayForm2[12]);tv13.setTextColor(Color.parseColor("#FF009900"));
                tv14.setText(dayForm2[13]);tv14.setTextColor(Color.parseColor("#FF009900"));
                break;
            case 2:
                tv1.setText(dayForm3[0]);tv1.setTextColor(Color.parseColor("#FF009900"));
                tv2.setText(dayForm3[1]);
                tv3.setText(dayForm3[2]);tv3.setTextColor(Color.parseColor("#FF009900"));
                tv4.setText(dayForm3[3]);
                tv5.setText(dayForm3[4]);
                tv6.setText(dayForm3[5]);
                tv7.setText(dayForm3[6]);
                tv8.setText(dayForm3[7]);
                tv9.setText(dayForm3[8]);
                tv10.setText(dayForm3[9]);
                tv11.setText(dayForm3[10]);
                tv12.setText(dayForm3[11]);
                tv13.setText(dayForm3[12]);tv13.setTextColor(Color.parseColor("#FF009900"));
                tv14.setText(dayForm3[13]);tv14.setTextColor(Color.parseColor("#FF009900"));
                break;
            case 3:
                tv1.setText(dayForm4[0]);tv1.setTextColor(Color.parseColor("#FF009900"));
                tv2.setText(dayForm4[1]);
                tv3.setText(dayForm4[2]);tv3.setTextColor(Color.parseColor("#FF009900"));
                tv4.setText(dayForm4[3]);
                tv5.setText(dayForm4[4]);
                tv6.setText(dayForm4[5]);
                tv7.setText(dayForm4[6]);
                tv8.setText(dayForm4[7]);
                tv9.setText(dayForm4[8]);
                tv10.setText(dayForm4[9]);
                tv11.setText(dayForm4[10]);
                tv12.setText(dayForm4[11]);
                tv13.setText(dayForm4[12]);tv13.setTextColor(Color.parseColor("#FF009900"));
                tv14.setText(dayForm4[13]);tv14.setTextColor(Color.parseColor("#FF009900"));
                break;
            default:
                tv1.setText(formOrder.get(0));tv1.setTextColor(Color.parseColor("#FF009900"));
                tv2.setText(formOrder.get(1));
                tv3.setText(formOrder.get(2));
                tv4.setText(formOrder.get(3));
                tv5.setText(formOrder.get(4));
                tv6.setText(formOrder.get(5));
                tv7.setText(formOrder.get(6));
                tv8.setText(formOrder.get(7));
                tv9.setText(formOrder.get(8));
                tv10.setText(formOrder.get(9));
                tv11.setText(formOrder.get(10));
                tv12.setText(formOrder.get(11));
                tv13.setText(formOrder.get(12));
                tv14.setText(formOrder.get(13));tv14.setTextColor(Color.parseColor("#FF009900"));
                break;
        }

    }

    //生成五個頁面的函式
    public void startPage(){
        viewPager3.setAdapter(new DayFormFragmentAdapter(getSupportFragmentManager(),getLifecycle(),formFrontList,formOrderList));
        new TabLayoutMediator(tabLayout3, viewPager3,true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                viewPager3.setCurrentItem(tab.getPosition());
                //預設初始頁面為本階製令
                viewPager3.setCurrentItem(1);
                textviewForm(1);
                //設定初始按下右鍵會跳轉頁面到索引值為1的位置
                imgRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager3.setCurrentItem(2);
                    }
                });
                imgLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager3.setCurrentItem(0);
                    }
                });

                //設定初始讓tab的樣式為 tab_style2
                tabLayout3.setSelectedTabIndicator(R.drawable.tab_style2);
            }
        }).attach();
        //放入5個tab的名稱
        for(int i = 0 ; i < tabLayout3.getTabCount() ; i++){
            tabLayout3.getTabAt(i).setText(tab_title1[i]);
        }
        //設定框線之間的短框線
        LinearLayout linearLayout = (LinearLayout) tabLayout3.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,R.drawable.green));

        //抓取tab索引值
        tabLayout3.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int selectedIndex = tab.getPosition();

                //控制左右鍵顯示，以及套用哪個 tab_style
                if(selectedIndex==0)
                {
                    tabLayout3.setSelectedTabIndicator(R.drawable.tab_style1);
                    imgLeft.setVisibility(View.INVISIBLE);
                    imgRight.setVisibility(View.VISIBLE);
                }
                else if(selectedIndex==4)
                {
                    tabLayout3.setSelectedTabIndicator(R.drawable.tab_style3);
                    imgRight.setVisibility(View.INVISIBLE);
                    imgLeft.setVisibility(View.VISIBLE);
                }
                else
                {
                    tabLayout3.setSelectedTabIndicator(R.drawable.tab_style2);
                    imgRight.setVisibility(View.VISIBLE);
                    imgLeft.setVisibility(View.VISIBLE);
                }

                //印出各頁面的資料
                textviewForm(selectedIndex);

                //左右鍵跳轉頁面
                imgLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager3.setCurrentItem(tab.getPosition()-1);
                    }
                });
                imgRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager3.setCurrentItem(tab.getPosition()+1);
                    }
                });
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}