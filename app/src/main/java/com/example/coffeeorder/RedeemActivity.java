package com.example.coffeeorder;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RedeemActivity extends AppCompatActivity {
    private final DataManager dataManager = DataManager.getInstance();
    private List<Coffee> rewardCoffeeList = dataManager.getRewardCoffeeList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());

        RecyclerView rvRedeemItemList = findViewById(R.id.redeem_item_list);
        rvRedeemItemList.setLayoutManager(new LinearLayoutManager(this));
        rvRedeemItemList.setAdapter(new RedeemItemAdapter(rewardCoffeeList));
    }
}

