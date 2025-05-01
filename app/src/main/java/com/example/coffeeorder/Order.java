package com.example.coffeeorder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class Order extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_order, container, false);

        ViewPager2 viewPager = view.findViewById(R.id.view_pager);
        OrdersPagerAdapter ordersPagerAdapter = new OrdersPagerAdapter(requireActivity());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(ordersPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText("On going");
            } else {
                tab.setText("History");
            }
        }).attach();

        return view;
    }
}
