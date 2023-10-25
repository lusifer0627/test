package com.example.test.ui.Schedule;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.test.R;
import com.example.test.ui.Schedule.Search.Search;
import com.example.test.ui.Schedule.Setting.Setting;

public class FragmentSch extends Fragment {
    private Button btn_sch_search,btn_sch_set;
    private String username;
    public FragmentSch() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View view_sch = inflater.inflate(R.layout.fragment_sch, container, false);

        btn_sch_search = view_sch.findViewById(R.id.button);
        btn_sch_set = view_sch.findViewById(R.id.button7);

        //接收名字
        getName();

        return view_sch;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //查詢進度表按鈕
        btn_sch_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳頁，並將名字傳給下個頁面
                Intent intent = new Intent();
                intent.setClass(getActivity(), Search.class);
                intent.putExtra("usernameKey_search",username);
                startActivity(intent);
            }
        });
        //設定按鈕
        btn_sch_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳頁，並將名字傳給下個頁面
                Intent intent = new Intent();
                intent.setClass(getActivity(), Setting.class);
                intent.putExtra("usernameKey_setting",username);
                startActivity(intent);
            }
        });
    }

    //接收名字的函式
    public void getName(){
        //接收上個頁面的名字資料
        Bundle args = getArguments();
        if (args != null) {
            username = args.getString("usernameKey_sch");
        }
    }
}