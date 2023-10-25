package com.example.test.ui.DaySch.DayForm;

import android.util.Log;


import com.example.test.ui.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DayFormPresenter implements DayFormContract.presenter{
    private DayFormContract.view callBake;
    public DayFormPresenter(DayFormContract.view view) {
        this.callBake=view;
    }
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList5 = new ArrayList<>();
    ArrayList<String> frontarrayList = new ArrayList<>();
    ArrayList<String> orderarrayList = new ArrayList<>();
    @Override
    public void SearchFront(String so_id,String item_id,String token)
    {
        Api.api.getFront(so_id,item_id,token).enqueue(new Callback<List<DayFormFrontResponse>>() {
            @Override
            public void onResponse(Call<List<DayFormFrontResponse>> call, Response<List<DayFormFrontResponse>> response) {
                Log.d("title", "請求成功");
                arrayList.clear();

                arrayList.add(String.format(response.body().get(0).mo_id));
                arrayList.add(String.format(response.body().get(0).so_id));
                arrayList.add(String.format(response.body().get(0).item_id));
                arrayList.add(String.format(response.body().get(0).item_name));
                arrayList.add("預計上線：");
                arrayList.add("生產數量：");
                arrayList.add(String.format(response.body().get(0).online_date));
                arrayList.add(String.format(response.body().get(0).qty));
                arrayList.add("計劃開始：");
                arrayList.add("計劃結束：");
                arrayList.add("08:00");
                arrayList.add("10:00");
                arrayList.add(String.format(response.body().get(0).related_tech_route.tech_routing_name));
                arrayList.add("生效");

                callBake.searchFrontReturn(arrayList);
            }

            @Override
            public void onFailure(Call<List<DayFormFrontResponse>> call, Throwable t) {
                Log.d("title",t.toString());
            }
        });
    }
    @Override
    public void SearchOrder(String sale_order,String customer,String online_date,String token)
    {
        Api.api.getOrder(sale_order,"","",token).enqueue(new Callback<List<DayFormOrderResponse>>() {
            @Override
            public void onResponse(Call<List<DayFormOrderResponse>> call, Response<List<DayFormOrderResponse>> response) {
                Log.d("title", "請求成功");

                arrayList5.add(String.format(response.body().get(0).so_id));
                arrayList5.add("");
                arrayList5.add("");
                arrayList5.add("");
                arrayList5.add("客戶名稱：");
                arrayList5.add("客戶訂單：");
                arrayList5.add(String.format(response.body().get(0).sale_order.get(0).customer_name));
                arrayList5.add(String.format(response.body().get(0).sale_order.get(0).customer_order));
                arrayList5.add("業務人員：");
                arrayList5.add("");
                arrayList5.add(String.format(response.body().get(0).sale_order.get(0).person_id));
                arrayList5.add("");
                arrayList5.add("");
                arrayList5.add("生效");

                callBake.searchOrderReturn(arrayList5);
            }

            @Override
            public void onFailure(Call<List<DayFormOrderResponse>> call, Throwable t) {
                Log.d("title",t.toString());
            }
        });
    }
    @Override
    public void SearchFrontList(String so_id,String item_id,String token)
    {
        Api.api.getFront(so_id,item_id,token).enqueue(new Callback<List<DayFormFrontResponse>>() {
            @Override
            public void onResponse(Call<List<DayFormFrontResponse>> call, Response<List<DayFormFrontResponse>> response) {
                Log.d("title", "請求成功");

                //中間資訊
                frontarrayList.add(String.format(response.body().get(0).so_id));
                frontarrayList.add(String.format(response.body().get(0).mo_id));
                frontarrayList.add(String.format(response.body().get(0).item_id));
                frontarrayList.add(String.format(response.body().get(0).item_name));
                frontarrayList.add(String.format(response.body().get(0).qty));

                //下方資訊
                frontarrayList.add(String.format(response.body().get(0).item_id));
                frontarrayList.add(String.format(response.body().get(0).item_name));
                frontarrayList.add(String.format("1.00"));
                frontarrayList.add(String.format(response.body().get(0).qty));
                frontarrayList.add(String.format("PCS"));
                frontarrayList.add(String.format(""));

                callBake.searchFrontListReturn(frontarrayList);
            }

            @Override
            public void onFailure(Call<List<DayFormFrontResponse>> call, Throwable t) {
                Log.d("title",t.toString());
            }
        });
    }
    @Override
    public void SearchOrderList(String sale_order,String customer,String online_date,String token)
    {
        Api.api.getOrder(sale_order,"","",token).enqueue(new Callback<List<DayFormOrderResponse>>() {
            @Override
            public void onResponse(Call<List<DayFormOrderResponse>> call, Response<List<DayFormOrderResponse>> response) {
                Log.d("title", "請求成功");

                //中間資訊
                orderarrayList.add(String.format(response.body().get(0).so_id));
                orderarrayList.add(String.format(response.body().get(0).sale_order.get(0).customer_name));
                orderarrayList.add(String.format(response.body().get(0).sale_order.get(0).customer_order));
                orderarrayList.add(String.format(response.body().get(0).sale_order.get(0).person_id));

                //下方資訊
                orderarrayList.add(String.format(response.body().get(0).sale_order.get(0).item));
                orderarrayList.add(String.format(response.body().get(0).sale_order.get(0).material_spec));
                orderarrayList.add(String.format(response.body().get(0).sale_order.get(0).qty));
                orderarrayList.add(String.format(response.body().get(0).sale_order.get(0).sunit_id));
                orderarrayList.add(String.format(response.body().get(0).sale_order.get(0).untrans_qty));
                orderarrayList.add(String.format("2023/9/19"));

                callBake.searchOrderListReturn(orderarrayList);
            }

            @Override
            public void onFailure(Call<List<DayFormOrderResponse>> call, Throwable t) {
                Log.d("title",t.toString());
            }
        });
    }
}
