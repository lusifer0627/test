package com.example.test.ui.Schedule.Search;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.test.ui.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchContract.presenter{
    private SearchContract.view callBake;
    private Context context;
    public SearchPresenter(SearchContract.view view,Context context) {
        this.callBake=view;
        this.context=context;
    }
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    public void SearchSo(String customer_so,String token)
    {
        Api.api.getSearchSo(customer_so,token).enqueue(new Callback<List<SearchResponse>>() {
            @Override
            public void onResponse(Call<List<SearchResponse>> call, Response<List<SearchResponse>> response) {
                Log.d("title", "請求成功");
                ArrayList<String> data=new ArrayList<String>();
                for (int i=0 ; i<response.body().size() ; i++) {
                    data.add(response.body().get(i).so_id);
                }
                callBake.SoDataReturn(data);
            }

            @Override
            public void onFailure(Call<List<SearchResponse>> call, Throwable t) {
                Log.d("title",t.toString());
            }
        });
    }
    @Override
    public void SearchName(String customer_name,String token)
    {
        Api.api.getSearchCustomer(customer_name,token).enqueue(new Callback<List<SearchResponse>>() {
            @Override
            public void onResponse(Call<List<SearchResponse>> call, Response<List<SearchResponse>> response) {
                Log.d("title", "請求成功");
                ArrayList<String> data=new ArrayList<>();
                for (int i=0 ; i<response.body().size() ; i++) {
                    data.add(response.body().get(i).customer_name);
                }
                callBake.NameDataReturn(data);
            }

            @Override
            public void onFailure(Call<List<SearchResponse>> call, Throwable t) {
                Log.d("title",t.toString());
            }
        });
    }
    @Override
    public void Search(String date,String customer_so,String  customer_name,String token)
    {
        //產生一個進度條
        ProgressDialog dialog_wait= new ProgressDialog(context);
        dialog_wait.setTitle("正在取得資料");
        dialog_wait.setCancelable(false);
        dialog_wait.setMessage("等待中...");
        dialog_wait.show();

        Api.api.getSearch(date,customer_so,customer_name,token).enqueue(new Callback<List<SearchResponse>>() {
            @Override
            public void onResponse(Call<List<SearchResponse>> call, Response<List<SearchResponse>> response) {
                Log.d("title", "請求成功");
                int size;
                size=response.body().size();
                //將之前儲存的資料清除
                arrayList.clear();
                for(int i=0;i<response.body().size();i++)
                {
                    Log.d("title",response.body().get(i).id);
                    Log.d("title",response.body().get(i).mo_id);
                    Log.d("title",response.body().get(i).so_id);
                    Log.d("title",response.body().get(i).item_id);
                    Log.d("title",response.body().get(i).customer);
                    Log.d("title",response.body().get(i).qty);
                    Log.d("title",response.body().get(i).complete_date);
                    Log.d("title",response.body().get(i).online_date);
                    Log.d("title",response.body().get(i).related_tech_route.tech_routing_name);
                    arrayList.add(String.format(response.body().get(i).mo_id));
                    arrayList.add(String.format(response.body().get(i).so_id));
                    arrayList.add(String.format(response.body().get(i).item_id));
                    arrayList.add(String.format(response.body().get(i).customer));
                    arrayList.add(String.format(response.body().get(i).qty));
                    arrayList.add(String.format(response.body().get(i).complete_date));
                    arrayList.add(String.format(response.body().get(i).online_date));
                    arrayList.add(String.format(response.body().get(i).related_tech_route.tech_routing_name));
                }
                callBake.searchReturn(size,arrayList);

                //將進度條消失
                dialog_wait.dismiss();
            }

            @Override
            public void onFailure(Call<List<SearchResponse>> call, Throwable t) {
                Log.d("title",t.toString());
                dialog_wait.dismiss();
                Toast.makeText(context, "無法取得資料，請檢查網路是否連線", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
