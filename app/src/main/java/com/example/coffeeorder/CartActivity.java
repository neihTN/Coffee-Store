package com.example.coffeeorder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    private final DataManager dataManager = DataManager.getInstance();
    private DataManager.CartChangedListener cartListener;
    List<OrderItem> itemList;

    private ImageButton backButton;
    private TextView totalPrice;
    private Button checkoutButton;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        itemList = dataManager.getCartItems();

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());

        checkoutButton = findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(v -> {
            if (itemList.size() == 0) {
                Toast.makeText(this, "No order in cart!", Toast.LENGTH_LONG).show();
                return;
            }

            for (OrderItem item : itemList)
                dataManager.addOrderToOngoing(item);
            dataManager.clearCart();

            Intent intent = new Intent(this, OrderSuccessActivity.class);
            startActivity(intent);
        });

        totalPrice = findViewById(R.id.total_price);
        updateTotalPrice();

        RecyclerView rvCartItemList = findViewById(R.id.rv_item_list);
        rvCartItemList.setLayoutManager(new LinearLayoutManager(this));
        CartItemAdapter adapter = new CartItemAdapter(itemList);
        rvCartItemList.setAdapter(adapter);

        cartListener = () -> {
            updateTotalPrice();
            adapter.notifyDataSetChanged();
        };
        dataManager.addCartListener(cartListener);
    }

    private void updateTotalPrice() {
        double total = 0;
        for (OrderItem item : itemList)
            total += item.getQuantity() * item.getCoffee().getPrice();
        totalPrice.setText("$" + String.format(Locale.UK, "%.2f", total));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataManager.removeCartListener(cartListener);
    }
}
