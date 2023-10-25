package com.example.test.ui.DaySch;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.ui.DaySch.DayForm.DayForm;

import java.util.ArrayList;
import java.util.HashMap;


public class FragmentDay extends Fragment {
    private RecyclerView mRecyclerView;
    private MyListAdapter myListAdapter;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    private String username="";
    public FragmentDay() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view_day = inflater.inflate(R.layout.fragment_day, container, false);
        mRecyclerView = view_day.findViewById(R.id.recycleview);

        //接收名字
        getName();

        return view_day;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //創建假資料
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("Id", Integer.toString(i+1));
            hashMap.put("Num1", "1MO1812040031");
            hashMap.put("Num2", "1SO1811270009");
            hashMap.put("Num3", "F260011ATN-2");
            hashMap.put("Num4", "MATADOR");
            hashMap.put("Str1", "數量 ：");
            hashMap.put("Count", "3");
            hashMap.put("Str2", "結關日 ：");
            hashMap.put("Date", "2018-12-07");
            hashMap.put("Str3", "計劃開始 ：");
            hashMap.put("Time", "08:00");
            hashMap.put("Str4", "一群-點焊");

            arrayList.add(hashMap);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        myListAdapter = new MyListAdapter();
        mRecyclerView.setAdapter(myListAdapter);
    }

    //recycleView的適配器
    public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tvId, tvNum1, tvNum2,tvNum3,tvNum4,tvStr1,tvStr2,tvStr3,tvStr4,tvCount,tvDate,tvTime;
            public ViewHolder(View itemView) {
                super(itemView);
                tvId = itemView.findViewById(R.id.textView22);
                tvNum1 = itemView.findViewById(R.id.textView16);
                tvNum2 = itemView.findViewById(R.id.textView17);
                tvNum3 = itemView.findViewById(R.id.textView12);
                tvNum4 = itemView.findViewById(R.id.textView13);
                tvStr1 = itemView.findViewById(R.id.textView14);
                tvStr2 = itemView.findViewById(R.id.textView15);
                tvCount = itemView.findViewById(R.id.textView18);
                tvDate = itemView.findViewById(R.id.textView19);
                tvStr3 = itemView.findViewById(R.id.textView10);
                tvTime = itemView.findViewById(R.id.textView20);
                tvStr4 = itemView.findViewById(R.id.textView21);
            }
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.day_recycleview_item, parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvId.setText(arrayList.get(position).get("Id"));
            holder.tvNum1.setText(arrayList.get(position).get("Num1"));
            holder.tvNum2.setText(arrayList.get(position).get("Num2"));
            holder.tvNum3.setText(arrayList.get(position).get("Num3"));
            holder.tvNum4.setText(arrayList.get(position).get("Num4"));
            holder.tvStr1.setText(arrayList.get(position).get("Str1"));
            holder.tvStr2.setText(arrayList.get(position).get("Str2"));
            holder.tvCount.setText(arrayList.get(position).get("Count"));
            holder.tvDate.setText(arrayList.get(position).get("Date"));
            holder.tvStr3.setText(arrayList.get(position).get("Str3"));
            holder.tvTime.setText(arrayList.get(position).get("Time"));
            holder.tvStr4.setText(arrayList.get(position).get("Str4"));

            holder.itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick (View view) {
                    Intent intent = new Intent();
                    Bundle bundle=new Bundle();
                    intent.setClass (getContext(), DayForm.class);
                    //跳頁並將名字傳過去
                    bundle.putString("usernameKeyNoApi",username);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }

    //接收名字的函式
    public void getName(){
        Bundle args = getArguments();
        if (args != null) {
            username = args.getString("usernameKey_day");
        }
    }
}