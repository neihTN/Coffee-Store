package com.example.coffeeorder;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {
    private List<Coffee> coffeeList;
    private final DataManager dataManager = DataManager.getInstance();

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView coffeeCardView;
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.coffeeCardView = itemView.findViewById(R.id.coffee_card_view);
            this.imageView = itemView.findViewById(R.id.coffee_image);
            this.textView = itemView.findViewById(R.id.coffee_name);
        }
    }

    public CoffeeAdapter(List<Coffee> coffeeList) {
        this.coffeeList = coffeeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_coffee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Coffee coffee = coffeeList.get(position);
        holder.imageView.setImageResource(coffee.getImageId());
        holder.textView.setText(coffee.getName());
        holder.coffeeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataManager.setCurrentDetailCoffee(coffee);
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.coffeeList.size();
    }

}

