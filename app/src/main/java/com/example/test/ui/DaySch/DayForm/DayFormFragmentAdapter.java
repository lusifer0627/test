package com.example.test.ui.DaySch.DayForm;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.test.ui.DaySch.DayForm.FragmentDayForm1.FragmentDayFrom1;
import com.example.test.ui.DaySch.DayForm.FragmentDayForm2.FragmentDayFrom2;
import com.example.test.ui.DaySch.DayForm.FragmentDayForm3.FragmentDayFrom3;
import com.example.test.ui.DaySch.DayForm.FragmentDayForm4.FragmentDayFrom4;
import com.example.test.ui.DaySch.DayForm.FragmentDayForm5.FragmentDayFrom5;

import java.util.ArrayList;

public class DayFormFragmentAdapter extends FragmentStateAdapter {

    FragmentDayFrom1 fragment_day_from1;
    FragmentDayFrom2 fragment_day_from2;
    FragmentDayFrom3 fragment_day_from3;
    FragmentDayFrom4 fragment_day_from4;
    FragmentDayFrom5 fragment_day_from5;
    private ArrayList<String> formFrontList;
    private ArrayList<String> formOrderList;
    public DayFormFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<String> formFrontList, ArrayList<String> formOrderList) {
        super(fragmentManager, lifecycle);
        this.formFrontList = formFrontList;
        this.formOrderList = formOrderList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle1 = new Bundle();
        Bundle bundle5 = new Bundle();
        switch (position) {
            case 0:
                fragment_day_from1 = new FragmentDayFrom1();
                bundle1.putStringArrayList("key1",formFrontList);
                fragment_day_from1.setArguments(bundle1);
                return fragment_day_from1;
            case 1:
                fragment_day_from2 = new FragmentDayFrom2();
                return fragment_day_from2;
            case 2:
                fragment_day_from3 = new FragmentDayFrom3();
                return fragment_day_from3;
            case 3:
                fragment_day_from4 = new FragmentDayFrom4();
                return fragment_day_from4;
            default:
                fragment_day_from5 = new FragmentDayFrom5();
                bundle5.putStringArrayList("key5",formOrderList);
                fragment_day_from5.setArguments(bundle5);
                return fragment_day_from5;
        }
    }

    @Override
    public int getItemCount(){
        return 5;
    }
}