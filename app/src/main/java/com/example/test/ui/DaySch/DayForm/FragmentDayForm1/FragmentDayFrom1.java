package com.example.test.ui.DaySch.DayForm.FragmentDayForm1;

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

public class FragmentDayFrom1 extends Fragment {
    private RecyclerView mRecyclerView;
    private MyListAdapter myListAdapter;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    private ArrayList<String> arrayList_front;
    private TextView tv_so,tv_mo,tv_itemId,tv_itemName,tv_qty;

    public FragmentDayFrom1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewDayForm = inflater.inflate(R.layout.fragment_day_from1, container, false);
        mRecyclerView = viewDayForm.findViewById(R.id.DayFormRv1);

        tv_so=viewDayForm.findViewById(R.id.textView57);
        tv_mo=viewDayForm.findViewById(R.id.textView65);
        tv_itemId=viewDayForm.findViewById(R.id.textView68);
        tv_itemName=viewDayForm.findViewById(R.id.textView72);
        tv_qty=viewDayForm.findViewById(R.id.textView79);

        Bundle args = getArguments();
        if (args != null) {
            arrayList_front =args.getStringArrayList("key1");
        }

        tv_so.setText(arrayList_front.get(0));
        tv_mo.setText(arrayList_front.get(1));
        tv_itemId.setText(arrayList_front.get(2));
        tv_itemName.setText(arrayList_front.get(3));
        tv_qty.setText(arrayList_front.get(4));

        return viewDayForm;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (int i = 0; i < 1; i++) {
            HashMap<String, String> hashMap1 = new HashMap<>();
            hashMap1.put("Id", String.format("%2d", i + 1));
            hashMap1.put("item_id", String.format(arrayList_front.get(5)));
            hashMap1.put("item_name", String.format(arrayList_front.get(6)));
            hashMap1.put("unit_qty", String.format(arrayList_front.get(7)));
            hashMap1.put("qty", String.format(arrayList_front.get(8)));
            hashMap1.put("unit", String.format(arrayList_front.get(9)));
            hashMap1.put("storage", String.format(arrayList_front.get(10)));
            arrayList.add(hashMap1);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        myListAdapter = new MyListAdapter();
        mRecyclerView.setAdapter(myListAdapter);

    }

    private class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView Id,item_id,item_name,unit_qty,qty,unit,storage;
            public ViewHolder(View itemView) {
                super(itemView);
                Id = itemView.findViewById(R.id.textView46);
                item_id = itemView.findViewById(R.id.textView61);
                item_name = itemView.findViewById(R.id.textView63);
                unit_qty = itemView.findViewById(R.id.textView64);
                qty = itemView.findViewById(R.id.textView69);
                unit = itemView.findViewById(R.id.textView75);
                storage = itemView.findViewById(R.id.textView76);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_day_form1_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.Id.setText(arrayList.get(position).get("Id"));
            holder.item_id.setText(arrayList.get(position).get("item_id"));
            holder.item_name.setText(arrayList.get(position).get("item_name"));
            holder.unit_qty.setText(arrayList.get(position).get("unit_qty"));
            holder.qty.setText(arrayList.get(position).get("qty"));
            holder.unit.setText(arrayList.get(position).get("unit"));
            holder.storage.setText(arrayList.get(position).get("storage"));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

    }
}