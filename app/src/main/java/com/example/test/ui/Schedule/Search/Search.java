package com.example.test.ui.Schedule.Search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.ui.Schedule.Search.SearchList.SearchList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class Search extends AppCompatActivity implements SearchContract.view{
    private DatePickerDialog.OnDateSetListener datePicker;
    private String token="",username="";
    private ImageView back;
    private Dialog dialog;
    private Calendar calendar;
    private EditText edit_date,edit_customer_so,edit_customer_name;
    private TextView tv_username;
    private Spinner spinner;
    private Button button_date,button_so_id,button_customer_name,button_ok;
    private SearchPresenter presenter;
    static final String[] type= {"點焊"};
    private RecyclerView searchRecyclerView;
    private SearchAdapter searchAdapter;
    private ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();
    private int soIdAndName=0;
    private Context context=this;
//=============================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        findById();

        back();

        calendar = Calendar.getInstance();

        //綁定presenter
        presenter = new SearchPresenter(this,this);

        getName();
        getToken();

        dialog=new Dialog(this);
        dialog.setContentView(R.layout.search_dialog_recycleview);

        //spinner設定
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,type);
        spinner.setAdapter(adapter);
//=============================================================================================================

        dateSelect();

        //日期選擇按鈕
        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(Search.this,
                        datePicker,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        //訂單查詢按鈕
        button_so_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soIdAndName=1;
                searchRecyclerView = dialog.findViewById(R.id.searchrecycleview);
                searchSo();
            }
        });

        //客戶查詢按鈕
        button_customer_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soIdAndName=2;
                searchRecyclerView = dialog.findViewById(R.id.searchrecycleview);
                searchName();
            }
        });

        //確定按鈕
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
    }
//=============================================================================================================
//=============================================================================================================
    public void getToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("Token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("data_key", "Value");
    }
    @Override
    public void searchSo() {
        presenter.SearchSo(edit_customer_so.getText().toString(),token);
    }
    @Override
    public void searchName() {
        presenter.SearchName(edit_customer_name.getText().toString(),token);
    }
    @Override
    public void SoDataReturn(ArrayList<String> soData) {

        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        searchAdapter = new SearchAdapter();
        searchRecyclerView.setAdapter(searchAdapter);

        arrayList1.clear();
        for(int i=0;i<soData.size();i++)
        {
            Log.d("title", soData.get(i));
            HashMap<String, String> searchhashMap = new HashMap<>();
            searchhashMap.put("data", String.format(soData.get(i)));
            arrayList1.add(searchhashMap);
        }

        dialog.show();
    }
    @Override
    public void NameDataReturn(ArrayList<String> nameData) {

        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        searchAdapter = new SearchAdapter();
        searchRecyclerView.setAdapter(searchAdapter);

        arrayList1.clear();
        for(int i=0;i<nameData.size();i++)
        {
            Log.d("title", nameData.get(i));
            HashMap<String, String> searchhashMap = new HashMap<>();
            searchhashMap.put("data", String.format(nameData.get(i)));
            arrayList1.add(searchhashMap);
        }

        dialog.show();
    }
    @Override
    public void search() {
        presenter.Search(edit_date.getText().toString(),
                edit_customer_so.getText().toString(),
                edit_customer_name.getText().toString(),
                token);
    }
    @Override
    public void searchReturn(int size, ArrayList<String> arrayList) {
        //跳轉頁面到searchList
        Intent intent = new Intent();
        intent.setClass(Search.this, SearchList.class);
        intent.putStringArrayListExtra("srcListData",arrayList);
        intent.putExtra("usernameKey_schList",username);
        startActivity(intent);
    }
    public void findById(){
        //綁定元件
        tv_username = findViewById(R.id.textView2);
        edit_date = findViewById(R.id.editTextDate);
        edit_customer_so=findViewById(R.id.editTextTextPersonName5);
        edit_customer_name=findViewById(R.id.editTextTextPersonName6);
        button_date = findViewById(R.id.button8);
        button_so_id = findViewById(R.id.button11);
        button_customer_name = findViewById(R.id.button12);
        button_ok=findViewById(R.id.button2);
        spinner = findViewById(R.id.spinner);
        back=findViewById(R.id.imageView12);
    }
    public void getName(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            username = bundle.getString("usernameKey_search");
            tv_username.setText(username);
        }
    }
    public void back(){
        //返回鍵結束當前頁面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void dateSelect(){
        //日期選擇器
        datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH , month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                String myFormat = "yyyy/MM/dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.TAIWAN);
                edit_date.setText(sdf.format(calendar.getTime()));
            }
        };
    }
    private class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tvData;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvData = itemView.findViewById(R.id.textView_search);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.search_dialog_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int p=position;
            if(soIdAndName==1)
            {
                holder.tvData.setText(arrayList1.get(position).get("data"));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edit_customer_so.setText(arrayList1.get(p).get("data"));
                        dialog.dismiss();
                    }
                });
            }
            if(soIdAndName==2)
            {
                holder.tvData.setText(arrayList1.get(position).get("data"));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edit_customer_name.setText(arrayList1.get(p).get("data"));
                        dialog.dismiss();
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return arrayList1.size();
        }
    }
}