package com.example.test.ui.Main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.test.ui.DaySch.FragmentDay;
import com.example.test.ui.Message.FragmentMes;
import com.example.test.ui.Schedule.FragmentSch;

public class MainFragmentAdapter extends FragmentStateAdapter {

    FragmentSch fragment_sch;
    FragmentDay fragment_day;
    FragmentMes fragment_mes;
    private String username1,username2;
    public MainFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,String username1,String username2) {
        super(fragmentManager, lifecycle);
        this.username1=username1;
        this.username2=username2;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle=new Bundle();
        Bundle bundle1=new Bundle();
        switch (position) {
            case 0:
                fragment_sch = new FragmentSch();
                bundle.putString("usernameKey_sch",username1);
                fragment_sch.setArguments(bundle);
                return fragment_sch;
            case 1:
                fragment_day = new FragmentDay();
                bundle1.putString("usernameKey_day",username2);
                fragment_day.setArguments(bundle1);
                return fragment_day;
            default:
                fragment_mes = new FragmentMes();
                return fragment_mes;
        }
    }

    @Override
    public int getItemCount(){
        return 3;
    }
}
