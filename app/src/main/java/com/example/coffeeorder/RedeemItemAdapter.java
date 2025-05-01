package com.example.coffeeorder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

public class RedeemItemAdapter extends RecyclerView.Adapter<RedeemItemAdapter.ViewHolder> {
    private List<Coffee> itemList;
    private final DataManager dataManager = DataManager.getInstance();
    public RedeemItemAdapter(List<Coffee> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_redeem, parent, false);
        return new RedeemItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Coffee coffee = itemList.get(position);
        holder.coffeeImage.setImageResource(coffee.getImageId());
        holder.coffeeName.setText(coffee.getName());
        holder.validDate.setText(OrderDateFormatter.formatOrderDate(new Date()));
        holder.redeemButton.setText(String.valueOf(coffee.getPointToRedeem()) + " pts");
        holder.redeemButton.setOnClickListener(view -> dataManager.redeemCoffee(holder.redeemButton.getContext(), coffee));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView coffeeImage;
        TextView coffeeName;
        TextView validDate;
        Button redeemButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coffeeName = itemView.findViewById(R.id.coffee_name);
            coffeeImage = itemView.findViewById(R.id.coffee_image);
            validDate = itemView.findViewById(R.id.valid_date);
            redeemButton = itemView.findViewById(R.id.redeem_button);
        }
    }
}
