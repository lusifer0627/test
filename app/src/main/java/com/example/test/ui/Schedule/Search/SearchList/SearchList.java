package com.example.test.ui.Schedule.Search.SearchList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.ui.DaySch.DayForm.DayForm;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchList extends AppCompatActivity {
    private RecyclerView searchListRecyclerView;
    private SearchListAdapter searchListAdapter;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    private ImageView back;
    private TextView tvSize,tv_username,tv_noItem;
    private String username="";
    private ArrayList<String> receivedData=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlist);

        findById();

        getName();

        back();

        searchListRecyclerView = findViewById(R.id.searchlistrv);
        searchListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchListRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        searchListAdapter = new SearchListAdapter();
        searchListRecyclerView.setAdapter(searchListAdapter);

        //接收上一個頁面的資料
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            receivedData = extras.getStringArrayList("srcListData");

            for(int i=0;i<receivedData.size()/8;i++)
            {
                HashMap<String, String> searchhashMap = new HashMap<>();
                searchhashMap.put("id", String.valueOf(i+1));
                searchhashMap.put("mo_id", receivedData.get(0+8*i));
                searchhashMap.put("so_id", receivedData.get(1+8*i));
                searchhashMap.put("item_id", receivedData.get(2+8*i));
                searchhashMap.put("customer", receivedData.get(3+8*i));
                searchhashMap.put("qty", receivedData.get(4+8*i));
                searchhashMap.put("complete_date", receivedData.get(5+8*i));
                searchhashMap.put("online_date", receivedData.get(6+8*i));
                searchhashMap.put("tech_routing_name", receivedData.get(7+8*i));
                arrayList.add(searchhashMap);
            }
        }

        //檢查抓取的資料是否正確
        for(int i=0;i<receivedData.size();i++)
        {
            Log.d("title", receivedData.get(i));
        }

        //列印出資料筆數
        tvSize.setText(String.valueOf(receivedData.size()/8));
        if(receivedData.size()/8==0){
            tv_noItem.setText("查無資料");
        }
    }
//=============================================================================================================
//=============================================================================================================
    public void getName(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            username = bundle.getString("usernameKey_schList");
            tv_username.setText(username);
        }
    }
    public void findById(){
        back=findViewById(R.id.imageView22);
        tvSize=findViewById(R.id.textView117);
        tv_username=findViewById(R.id.textView111);
        tv_noItem=findViewById(R.id.textView110);
    }
    public void back(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder>{
        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_id,tv_moId,tv_soId,tv_itemId,tv_customer,tv_qty,tv_completeDate,tv_onlineDate,tv_techRoutingName;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_id = itemView.findViewById(R.id.textView38);
                tv_moId = itemView.findViewById(R.id.textView39);
                tv_soId = itemView.findViewById(R.id.textView48);
                tv_itemId = itemView.findViewById(R.id.textView96);
                tv_customer = itemView.findViewById(R.id.textView100);
                tv_qty = itemView.findViewById(R.id.textView103);
                tv_completeDate = itemView.findViewById(R.id.textView104);
                tv_onlineDate = itemView.findViewById(R.id.textView105);
                tv_techRoutingName = itemView.findViewById(R.id.textView107);
            }
        }

        @NonNull
        @Override
        public SearchListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.searchlist_item, parent, false);
            return new SearchListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SearchListAdapter.ViewHolder holder, int position) {
            //於item中顯示文字
            holder.tv_id.setText(arrayList.get(position).get("id"));
            holder.tv_moId.setText(arrayList.get(position).get("mo_id"));
            holder.tv_soId.setText(arrayList.get(position).get("so_id"));
            holder.tv_itemId.setText(arrayList.get(position).get("item_id"));
            holder.tv_customer.setText(arrayList.get(position).get("customer"));
            holder.tv_qty.setText(arrayList.get(position).get("qty"));
            holder.tv_completeDate.setText(arrayList.get(position).get("complete_date"));
            holder.tv_onlineDate.setText(arrayList.get(position).get("online_date"));
            holder.tv_techRoutingName.setText(arrayList.get(position).get("tech_routing_name"));

            //把點擊的資料傳給下個頁面
            ArrayList<String> arrayList1 =new ArrayList<>();
            arrayList1.add(arrayList.get(position).get("so_id"));
            arrayList1.add(arrayList.get(position).get("item_id"));
            arrayList1.add(arrayList.get(position).get("customer"));
            arrayList1.add(arrayList.get(position).get("online_date"));
            //跳轉到資料各自頁面
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(SearchList.this, DayForm.class);
                    intent.putExtra("usernameKey_api",username);
                    intent.putStringArrayListExtra("onceData",arrayList1);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return receivedData.size()/8;
        }
    }
}