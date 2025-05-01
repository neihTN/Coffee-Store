package com.example.coffeeorder;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Fragment homeFragment;
    private Fragment rewardsFragment;
    private Fragment ordersFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        homeFragment = new Home();
        rewardsFragment = new Reward();
        ordersFragment = new Order();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        if (savedInstanceState == null) {
            String targetFragment = null;

            if (getIntent() != null) {
                targetFragment = getIntent().getStringExtra("target_fragment");
            }

            if ("reward".equals(targetFragment)) {
                loadFragment(rewardsFragment);
                bottomNavigationView.setSelectedItemId(R.id.rewards); // Đồng bộ BottomNavigationView
            } else if ("order".equals(targetFragment)) {
                loadFragment(ordersFragment);
                bottomNavigationView.setSelectedItemId(R.id.orders);
            } else {
                loadFragment(homeFragment);
                bottomNavigationView.setSelectedItemId(R.id.homepage);
            }
        }

        bottomNavigationView.setOnApplyWindowInsetsListener(null);
        bottomNavigationView.setPadding(0, 0, 0, 0);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();
            if (id == R.id.homepage) {
                selectedFragment = homeFragment;
            } else if (id == R.id.rewards) {
                selectedFragment = rewardsFragment;
            } else if (id == R.id.orders) {
                selectedFragment = ordersFragment;
            }

            return loadFragment(selectedFragment);
        });
    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}