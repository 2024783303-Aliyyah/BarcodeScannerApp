package com.example.barcodescannerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final List<String> historyItems;
    private final Context context;

    public HistoryAdapter(Context context, List<String> historyItems) {
        this.context = context;
        this.historyItems = historyItems;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        String productName = historyItems.get(position);
        holder.productNameTextView.setText(productName);

        // ⭐ THIS IS THE CLICK ACTION ⭐
        holder.itemView.setOnClickListener(v -> {
            // 1. Create intent for the search page
            Intent intent = new Intent(context, SearchProductActivity.class);

            // 2. Attach the product name with the key "PRODUCT_NAME"
            intent.putExtra("PRODUCT_NAME", productName);

            // 3. Start the activity
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}

