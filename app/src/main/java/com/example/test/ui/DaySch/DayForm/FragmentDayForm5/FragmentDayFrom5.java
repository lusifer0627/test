package com.example.test.ui.DaySch.DayForm.FragmentDayForm5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentDayFrom5 extends Fragment {
    private RecyclerView mRecyclerView;
    private MyListAdapter myListAdapter;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    private ArrayList<String> arrayList_order;
    private TextView tv_so,tv_cname,tv_cOrder,tv_pid;
    public FragmentDayFrom5() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewDayForm = inflater.inflate(R.layout.fragment_day_from5, container, false);
        mRecyclerView = viewDayForm.findViewById(R.id.DayFormRv1);

        tv_so=viewDayForm.findViewById(R.id.textView90);
        tv_cname=viewDayForm.findViewById(R.id.textView95);
        tv_cOrder=viewDayForm.findViewById(R.id.textView73);
        tv_pid=viewDayForm.findViewById(R.id.textView77);

        Bundle args = getArguments();
        if (args != null) {
            arrayList_order =args.getStringArrayList("key5");
        }

        tv_so.setText(arrayList_order.get(0));
        tv_cname.setText(arrayList_order.get(1));
        tv_cOrder.setText(arrayList_order.get(2));
        tv_pid.setText(arrayList_order.get(3));

        return viewDayForm;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (int i = 0; i < 1; i++) {
            HashMap<String, String> hashMap1 = new HashMap<>();
            hashMap1.put("Id", String.format("%2d", i + 1));
            hashMap1.put("item", String.format(arrayList_order.get(4)));
            hashMap1.put("material_spec", String.format(arrayList_order.get(5)));
            hashMap1.put("qty", String.format(arrayList_order.get(6)));
            hashMap1.put("sUnit_id", String.format(arrayList_order.get(7)));
            hashMap1.put("unTrans_qty", String.format(arrayList_order.get(8)));
            hashMap1.put("date", String.format(arrayList_order.get(9)));
            arrayList.add(hashMap1);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        myListAdapter = new FragmentDayFrom5.MyListAdapter();
        mRecyclerView.setAdapter(myListAdapter);

    }

    private class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView Id,item,material_spec,qty,sUnit_id,unTrans_qty,date;
            public ViewHolder(View itemView) {
                super(itemView);
                Id = itemView.findViewById(R.id.textView46);
                item = itemView.findViewById(R.id.textView61);
                material_spec = itemView.findViewById(R.id.textView63);
                qty = itemView.findViewById(R.id.textView64);
                sUnit_id = itemView.findViewById(R.id.textView69);
                unTrans_qty = itemView.findViewById(R.id.textView75);
                date = itemView.findViewById(R.id.textView76);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_day_form5_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.Id.setText(arrayList.get(position).get("Id"));
            holder.item.setText(arrayList.get(position).get("item"));
            holder.material_spec.setText(arrayList.get(position).get("material_spec"));
            holder.qty.setText(arrayList.get(position).get("qty"));
            holder.sUnit_id.setText(arrayList.get(position).get("sUnit_id"));
            holder.unTrans_qty.setText(arrayList.get(position).get("unTrans_qty"));
            holder.date.setText(arrayList.get(position).get("date"));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }
}