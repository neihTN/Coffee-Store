package com.example.coffeeorder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Reward extends Fragment {
    private TextView tvPoints;
    private TextView tvCurrentStamps;
    private Button btRedeem;
    private RecyclerView rvLoyaltyStamp;
    private RecyclerView rvHistory;
    private CardView cvLoyaltyCard;

    private final DataManager dataManager = DataManager.getInstance();
    private DataManager.HistoryOrdersChangedListener historyListener;
    private DataManager.LoyaltyStampChangedListener loyaltyStampListener;
    private DataManager.PointChangedListener pointListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reward, container, false);

        tvPoints = view.findViewById(R.id.points);
        tvPoints.setText(String.valueOf(dataManager.getPoints()));

        btRedeem = view.findViewById(R.id.redeem);
        btRedeem.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), RedeemActivity.class);
            startActivity(intent);
        });

        tvCurrentStamps = view.findViewById(R.id.current_stamps);
        tvCurrentStamps.setText(dataManager.getCurrentStamps() + "/" + DataManager.MAX_STAMPS);

        cvLoyaltyCard = view.findViewById(R.id.loyalty_view);
        cvLoyaltyCard.setOnClickListener(v -> dataManager.updateLoyaltyStamp());

        rvHistory = view.findViewById(R.id.rv_history);
        rvHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        List<OrderItem> itemList = dataManager.getHistoryOrders();
        rvHistory.setAdapter(new HistoryItemAdapter(itemList));

        rvLoyaltyStamp = view.findViewById(R.id.rv_loyalty_stamp);
        rvLoyaltyStamp.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        LoyaltyStampAdapter rvLoyaltyStampAdapter = new LoyaltyStampAdapter(DataManager.MAX_STAMPS, dataManager.getCurrentStamps());
        rvLoyaltyStamp.setAdapter(rvLoyaltyStampAdapter);

        historyListener = () -> rvHistory.getAdapter().notifyItemInserted(itemList.size() - 1);
        dataManager.addHistoryListener(historyListener);

        pointListener = () -> tvPoints.setText(String.valueOf(dataManager.getPoints()));
        dataManager.addPointListener(pointListener);

        loyaltyStampListener = () -> {
            tvPoints.setText(String.valueOf(dataManager.getPoints()));
            rvLoyaltyStamp.setAdapter(new LoyaltyStampAdapter(DataManager.MAX_STAMPS, dataManager.getCurrentStamps()));
            tvCurrentStamps.setText(dataManager.getCurrentStamps() + "/" + DataManager.MAX_STAMPS);
        };
        dataManager.addLoyaltyStampListener(loyaltyStampListener);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dataManager.removeHistoryListener(historyListener);
        dataManager.removeLoyaltyStampListener(loyaltyStampListener);
        dataManager.removePointListener(pointListener);
    }
}
