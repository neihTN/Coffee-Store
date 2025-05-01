package com.example.coffeeorder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class Home extends Fragment {
    private DataManager dataManager = DataManager.getInstance();
    private DataManager.UserInformationChangedListener userInfolistener;
    private DataManager.LoyaltyStampChangedListener loyaltyStampListener;

    private RecyclerView rvCoffeeList;
    private RecyclerView rvLoyaltyStamp;
    private TextView tvUserName;
    private TextView tvCurrentStamps;
    private ImageButton btUserProfile;
    private ImageButton btCart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvCoffeeList = view.findViewById(R.id.rv_coffee_list);
        rvCoffeeList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        List<Coffee> coffeeList = dataManager.getCoffeeList();
        rvCoffeeList.setAdapter(new CoffeeAdapter(coffeeList));

        rvLoyaltyStamp = view.findViewById(R.id.rv_loyalty_stamp);
        rvLoyaltyStamp.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        LoyaltyStampAdapter rvLoyaltyStampAdapter = new LoyaltyStampAdapter(DataManager.MAX_STAMPS, dataManager.getCurrentStamps());
        rvLoyaltyStamp.setAdapter(rvLoyaltyStampAdapter);

        tvUserName = view.findViewById(R.id.user_name);
        tvUserName.setText(dataManager.getUserName());

        tvCurrentStamps = view.findViewById(R.id.current_stamps);
        tvCurrentStamps.setText(dataManager.getCurrentStamps() + "/" + DataManager.MAX_STAMPS);

        btUserProfile = view.findViewById(R.id.user_icon);
        btUserProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ProfileActivity.class);
            startActivity(intent);
        });

        btCart = view.findViewById(R.id.cart_icon);
        btCart.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CartActivity.class);
            startActivity(intent);
        });

        userInfolistener = () -> {tvUserName.setText(dataManager.getUserName());};
        dataManager.addUserInfoListener(userInfolistener);

        loyaltyStampListener = () -> {
            rvLoyaltyStamp.setAdapter(new LoyaltyStampAdapter(DataManager.MAX_STAMPS, dataManager.getCurrentStamps()));
            tvCurrentStamps.setText(dataManager.getCurrentStamps() + "/" + DataManager.MAX_STAMPS);
        };
        dataManager.addLoyaltyStampListener(loyaltyStampListener);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dataManager.removeUserInfoListener(userInfolistener);
        dataManager.removeLoyaltyStampListener(loyaltyStampListener);
    }
}