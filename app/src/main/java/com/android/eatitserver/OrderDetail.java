package com.android.eatitserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.widget.TextView;

import com.android.eatitserver.Common.Common;
import com.android.eatitserver.ViewHolder.OrderDetailAdapter;

public class OrderDetail extends AppCompatActivity {

    TextView orderId, orderPhone, orderAddress, orderTotal, orderComment;
    String orderIdValue = "";
    RecyclerView listFoods;
    LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        orderId = (TextView) findViewById(R.id.order_id);
        orderPhone = (TextView) findViewById(R.id.order_phone);
        orderAddress = (TextView) findViewById(R.id.order_address);
        orderTotal = (TextView) findViewById(R.id.order_total);
        orderComment = (TextView) findViewById(R.id.order_comment);

        listFoods = (RecyclerView) findViewById(R.id.listFoods);
        listFoods.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listFoods.setLayoutManager(layoutManager);

        if (getIntent() != null)
            orderIdValue = getIntent().getStringExtra("OrderId");

        orderId.setText(orderIdValue);
        orderPhone.setText(Common.currentRequest.getPhone());
        orderAddress.setText(Common.currentRequest.getAddress());
        orderTotal.setText(Common.currentRequest.getTotal());
        orderComment.setText(Common.currentRequest.getComment());

        OrderDetailAdapter adapter = new OrderDetailAdapter(Common.currentRequest.getFoods());
        adapter.notifyDataSetChanged();
        listFoods.setAdapter(adapter);
    }
}
