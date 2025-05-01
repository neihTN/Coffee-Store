package com.example.coffeeorder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OngoingOrdersFragment extends Fragment {
    private final DataManager dataManager = DataManager.getInstance();
    private DataManager.OngoingOrdersChangedListener ongoingListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_ongoing, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_ongoing);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<OrderItem> itemList = dataManager.getOngoingOrders();
        OrderAdapter adapter= new OrderAdapter(itemList);
        recyclerView.setAdapter(adapter);

        ongoingListener = () -> adapter.notifyItemInserted(itemList.size() - 1);
        dataManager.addOngoingListener(ongoingListener);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false; // Drag-and-drop not needed
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Get the swiped item's position
                int position = viewHolder.getAdapterPosition();

                // Move order to History and remove it from On-going
                OrderItem order = itemList.get(position);
                itemList.remove(position);
                adapter.notifyItemRemoved(position);

                dataManager.addOrderToHistory(order);
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dataManager.removeOngoingListener(ongoingListener);
    }
}

