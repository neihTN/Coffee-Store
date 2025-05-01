package com.example.coffeeorder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryOrdersFragment extends Fragment {
    private List<OrderItem> itemList;
    private OrderAdapter adapter;
    private DataManager dataManager = DataManager.getInstance();
    private DataManager.HistoryOrdersChangedListener listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemList = dataManager.getHistoryOrders();
        adapter = new OrderAdapter(itemList);
        recyclerView.setAdapter(adapter);

        listener = () -> adapter.notifyItemInserted(itemList.size() - 1);
        dataManager.addHistoryListener(listener);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dataManager.removeHistoryListener(listener);
    }
}

