package com.example.coffeeorder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {
    List<OrderItem> itemList;

    public CartItemAdapter(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderItem item = itemList.get(position);
        holder.coffeeImage.setImageResource(item.getCoffee().getImageId());
        holder.coffeeName.setText(item.getCoffee().getName());
        holder.price.setText("$" + String.format(Locale.US, "%.2f", item.getQuantity() * item.getCoffee().getPrice()));
        holder.details.setText(item.getDetails());
        holder.quantity.setText("x " + item.getQuantity());
        holder.deleteButton.setOnClickListener(v -> {
            DataManager.getInstance().removeOrderFromCart(item);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView coffeeImage;
        private TextView coffeeName;
        private TextView quantity;
        private TextView details;
        private TextView price;
        private CardView deleteButton;

        public CardView getDeleteButton() {
            return deleteButton;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coffeeImage = itemView.findViewById(R.id.coffee_image);
            coffeeName = itemView.findViewById(R.id.coffee_name);
            quantity = itemView.findViewById(R.id.quantity);
            details = itemView.findViewById(R.id.coffee_details);
            price = itemView.findViewById(R.id.price);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
