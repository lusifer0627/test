package com.example.test.ui.DaySch.DayForm.FragmentDayForm2;

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

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentDayFrom2 extends Fragment {

    RecyclerView mRecyclerView;
    FragmentDayFrom2.MyListAdapter myListAdapter;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    public FragmentDayFrom2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewDayForm = inflater.inflate(R.layout.fragment_day_from2, container, false);
        mRecyclerView = viewDayForm.findViewById(R.id.DayFormRv1);
        return viewDayForm;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (int i = 0; i < 30; i++) {
            HashMap<String, String> hashMap1 = new HashMap<>();
            hashMap1.put("Id", String.format("%2d", i + 1));
            arrayList.add(hashMap1);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        myListAdapter = new FragmentDayFrom2.MyListAdapter();
        mRecyclerView.setAdapter(myListAdapter);

    }

    private class MyListAdapter extends RecyclerView.Adapter<FragmentDayFrom2.MyListAdapter.ViewHolder>{

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView Id;
            public ViewHolder(View itemView) {
                super(itemView);
                Id = itemView.findViewById(R.id.textView46);
            }
        }

        @Override
        public FragmentDayFrom2.MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_day_form2_item, parent, false);
            return new FragmentDayFrom2.MyListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(FragmentDayFrom2.MyListAdapter.ViewHolder holder, int position) {
            holder.Id.setText(arrayList.get(position).get("Id"));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

    }
}