package com.example.coffeeorder;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class OrdersPagerAdapter extends FragmentStateAdapter {
    private final OngoingOrdersFragment ongoingOrdersFragment;
    private final HistoryOrdersFragment historyOrdersFragment;

    public OrdersPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        ongoingOrdersFragment = new OngoingOrdersFragment();
        historyOrdersFragment = new HistoryOrdersFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return ongoingOrdersFragment; // Tab On-goings
        } else {
            return historyOrdersFragment; // Tab History
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

