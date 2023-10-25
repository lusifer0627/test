package com.example.test.ui.DaySch.DayForm;


import java.util.ArrayList;

public interface DayFormContract {
    interface view{
        void searchFront();
        void searchOrder();
        void searchFrontList();
        void searchOrderList();
        void searchFrontReturn(ArrayList<String> arrayList);
        void searchFrontListReturn(ArrayList<String> arrayList_front);
        void searchOrderReturn(ArrayList<String> arrayList5);
        void searchOrderListReturn(ArrayList<String> arrayList_order);
    }
    interface presenter{
        void SearchFront(String so_id,String item_id,String token);
        void SearchOrder(String sale_order,String customer,String online_date,String token);
        void SearchFrontList(String so_id,String item_id,String token);
        void SearchOrderList(String sale_order,String customer,String online_date,String token);

    }
}
