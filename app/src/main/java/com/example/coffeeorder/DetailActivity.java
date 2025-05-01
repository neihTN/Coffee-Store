package com.example.coffeeorder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.Locale;

public class DetailActivity  extends AppCompatActivity {
    private final DataManager dataManager = DataManager.getInstance();

    private TextView selectedShot;
    private ImageButton selectedType;
    private ImageButton selectedSize;
    private ImageButton selectedIce;

    private TextView decreaseButton;
    private TextView increaseButton;
    private TextView singleShot;
    private TextView doubleShot;
    private ImageButton dineIn;
    private ImageButton takeaway;
    private ImageButton bigSize;
    private ImageButton mediumSize;
    private ImageButton smallSize;
    private ImageButton fullIce;
    private ImageButton halfIce;
    private ImageButton littleIce;
    private TextView quantityView;
    private TextView amountView;
    private int quantity;
    private Button addToCartButton;
    private ImageButton cartButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get views
        decreaseButton = findViewById(R.id.decrease_button);
        increaseButton = findViewById(R.id.increase_button);
        singleShot = findViewById(R.id.single_button);
        doubleShot = findViewById(R.id.double_button);
        dineIn = findViewById(R.id.dine_in_button);
        takeaway = findViewById(R.id.takeaway_button);
        bigSize = findViewById(R.id.big_size_button);
        mediumSize = findViewById(R.id.medium_size_button);
        smallSize = findViewById(R.id.small_size_button);
        fullIce = findViewById(R.id.full_ice_button);
        halfIce = findViewById(R.id.half_ice_button);
        littleIce = findViewById(R.id.little_ice_button);
        quantityView = findViewById(R.id.quantity);
        amountView = findViewById(R.id.total_amount);

        cartButton = findViewById(R.id.cart_button);
        addToCartButton = findViewById(R.id.add_to_cart_button);
        ImageButton backButton = findViewById(R.id.back_button);
        ImageView coffeeImage = findViewById(R.id.coffee_image);

        // Default data
        selectedShot = singleShot;
        selectedType = takeaway;
        selectedSize = bigSize;
        selectedIce = fullIce;

        Coffee coffee = dataManager.getCurrentDetailCoffee();
        selectShot();
        selectType();
        selectSize();
        selectIce();
        quantity = 1;
        quantityView.setText(String.valueOf(quantity));
        amountView.setText("$" + String.format(Locale.US, "%.2f", coffee.getPrice() * quantity));
        coffeeImage.setImageResource(coffee.getImageId());

        // CLick events
        decreaseButton.setOnClickListener(v -> decreaseQuantity());
        increaseButton.setOnClickListener(v -> increaseQuantity());
        singleShot.setOnClickListener(v -> {
            selectedShot = singleShot;
            selectShot();
        });
        doubleShot.setOnClickListener(v -> {
            selectedShot = doubleShot;
            selectShot();
        });
        takeaway.setOnClickListener(v -> {
            selectedType = takeaway;
            selectType();
        });
        dineIn.setOnClickListener(v -> {
            selectedType = dineIn;
            selectType();
        });
        bigSize.setOnClickListener(v -> {
            selectedSize = bigSize;
            selectSize();
        });
        mediumSize.setOnClickListener(v -> {
            selectedSize = mediumSize;
            selectSize();
        });
        smallSize.setOnClickListener(v -> {
            selectedSize = smallSize;
            selectSize();
        });
        fullIce.setOnClickListener(v -> {
            selectedIce = fullIce;
            selectIce();
        });
        halfIce.setOnClickListener(v -> {
            selectedIce = halfIce;
            selectIce();
        });
        littleIce.setOnClickListener(v -> {
            selectedIce = littleIce;
            selectIce();
        });

        backButton.setOnClickListener(v -> finish());
        addToCartButton.setOnClickListener(v -> {
            String details = makeDetails();
            dataManager.addOrderToCart(new OrderItem(coffee, quantity, new Date(),dataManager.getUserAddress(), details));
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });

        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });
    }

    private String makeDetails() {
        String details = "";

        details += selectedShot == singleShot ? "single | " : "double | ";
        details += "iced | ";
        if (selectedSize == bigSize)
            details += "big | ";
        else if (selectedSize == mediumSize)
            details += "medium | ";
        else
            details += "small | ";

        if (selectedIce == fullIce)
            details += "full ice";
        else if (selectedIce == halfIce)
            details += "half ice";
        else
            details += "few ice";

        return details;
    }

    public void selectShot() {
        if (selectedShot == singleShot) {
            singleShot.setTextColor(getResources().getColor(R.color.black));
            doubleShot.setTextColor(getResources().getColor(R.color.gray));
        }
        else {
            singleShot.setTextColor(getResources().getColor(R.color.gray));
            doubleShot.setTextColor(getResources().getColor(R.color.black));
        }
    }

    public void selectSize() {
        if (selectedSize == bigSize) {
            bigSize.setColorFilter(getResources().getColor(R.color.black));
            mediumSize.setColorFilter(getResources().getColor(R.color.gray));
            smallSize.setColorFilter(getResources().getColor(R.color.gray));
        }
        else if (selectedSize == mediumSize) {
            bigSize.setColorFilter(getResources().getColor(R.color.gray));
            mediumSize.setColorFilter(getResources().getColor(R.color.black));
            smallSize.setColorFilter(getResources().getColor(R.color.gray));
        }
        else {
            bigSize.setColorFilter(getResources().getColor(R.color.gray));
            mediumSize.setColorFilter(getResources().getColor(R.color.gray));
            smallSize.setColorFilter(getResources().getColor(R.color.black));
        }
    }

    public void selectType() {
        if (selectedType == dineIn) {
            takeaway.setColorFilter(getResources().getColor(R.color.gray));
            dineIn.setColorFilter(getResources().getColor(R.color.black));
        }
        else {
            takeaway.setColorFilter(getResources().getColor(R.color.black));
            dineIn.setColorFilter(getResources().getColor(R.color.gray));
        }
    }

    public void selectIce() {
        if (selectedIce == fullIce) {
            fullIce.setColorFilter(getResources().getColor(R.color.black));
            halfIce.setColorFilter(getResources().getColor(R.color.gray));
            littleIce.setColorFilter(getResources().getColor(R.color.gray));
        }
        else if (selectedIce == halfIce) {
            fullIce.setColorFilter(getResources().getColor(R.color.gray));
            halfIce.setColorFilter(getResources().getColor(R.color.black));
            littleIce.setColorFilter(getResources().getColor(R.color.gray));
        }
        else {
            fullIce.setColorFilter(getResources().getColor(R.color.gray));
            halfIce.setColorFilter(getResources().getColor(R.color.gray));
            littleIce.setColorFilter(getResources().getColor(R.color.black));
        }
    }

    public void increaseQuantity() {
        quantity += 1;
        quantityView.setText(String.valueOf(quantity));
        amountView.setText("$" + String.format(Locale.US, "%.2f", dataManager.getCurrentDetailCoffee().getPrice() * quantity));
    }

    public void decreaseQuantity() {
        if (quantity > 1) {
            quantity -= 1;
            quantityView.setText(String.valueOf(quantity));
            amountView.setText("$" + String.format(Locale.US, "%.2f", dataManager.getCurrentDetailCoffee().getPrice() * quantity));
        }
    }
}
