package com.example.coffeeorder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<OrderItem> itemList;

    public OrderAdapter(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderItem item = itemList.get(position);
        holder.address.setText(item.getAddress());
        holder.coffeeName.setText(item.getCoffee().getName());
        holder.orderDate.setText(OrderDateFormatter.formatOrderDate(item.getOrderDate()));
        holder.price.setText("$" + String.format(Locale.US, "%.2f", item.getQuantity() * item.getCoffee().getPrice()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView coffeeName;
        private TextView address;
        private TextView price;
        private TextView orderDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coffeeName = itemView.findViewById(R.id.coffee_name);
            address = itemView.findViewById(R.id.address);
            price = itemView.findViewById(R.id.price);
            orderDate = itemView.findViewById(R.id.order_date);
        }
    }
}
