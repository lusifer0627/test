package com.example.test.ui.Schedule.Search;

import java.util.ArrayList;
import java.util.HashMap;

public interface SearchContract {
    interface view{
        void searchName();
        void searchSo();
        void SoDataReturn(ArrayList<String> sd);
        void NameDataReturn(ArrayList<String> nd);
        void search();
        void searchReturn(int size,ArrayList <String> arrayList);

    }
    interface presenter{
        void SearchSo(String customer_so,String token);
        void SearchName(String customer_name,String token);
        void Search(String date,String customer_so,String  customer_name,String token);
    }
}
