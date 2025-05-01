package com.example.coffeeorder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LoyaltyStampAdapter extends RecyclerView.Adapter<LoyaltyStampAdapter.ViewHolder> {
    private int MAX_STAMPS;
    private int currentStamps;

    public LoyaltyStampAdapter(int MAX_STAMPS, int currentStamps) {
        this.MAX_STAMPS = MAX_STAMPS;
        this.currentStamps = currentStamps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loyalty_stamp, parent, false);
        return new LoyaltyStampAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position < currentStamps)
            holder.imageView.setImageResource(R.drawable.stamp);
        else
            holder.imageView.setImageResource(R.drawable.blur_stamp);
    }

    @Override
    public int getItemCount() {
        return MAX_STAMPS;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.stampImageView);
        }
    }
}
