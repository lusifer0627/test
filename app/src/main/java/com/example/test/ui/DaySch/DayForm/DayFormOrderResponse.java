package com.example.test.ui.DaySch.DayForm;

import java.util.List;

public class DayFormOrderResponse {
    public String so_id;
    public List<Sale_order> sale_order;
    public class Sale_order{
        public String item;
        public String material_spec;
        public String qty;
        public String sunit_id;
        public String untrans_qty;
        public String customer_name;
        public String customer_order;
        public String person_id;
    }
}
