package com.example.coffeeorder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemAdapter.ViewHolder> {
    private List<OrderItem> itemList;

    public HistoryItemAdapter(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new HistoryItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderItem item = itemList.get(position);
        holder.coffeeName.setText(item.getCoffee().getName());
        holder.orderDate.setText(OrderDateFormatter.formatOrderDate(item.getOrderDate()));
        holder.plusPoints.setText("+ " + item.getQuantity() * item.getCoffee().getPointPerCup() + " Pts");
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView coffeeName;
        private TextView plusPoints;
        private TextView orderDate;


       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           this.coffeeName = itemView.findViewById(R.id.coffee_name);
           this.orderDate = itemView.findViewById(R.id.order_date);
           this.plusPoints = itemView.findViewById(R.id.plus_points);
       }
   }
}
