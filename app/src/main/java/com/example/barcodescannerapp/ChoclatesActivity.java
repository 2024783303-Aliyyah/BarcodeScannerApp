package com.example.barcodescannerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ChoclatesActivity extends RecyclerView.Adapter<ChoclatesActivity.ChocolateViewHolder> implements Filterable {

    private List<String> chocolateList;
    private List<String> chocolateListFull; // Senarai asal untuk fungsi carian
    private Context context;

    public ChoclatesActivity(Context context, List<String> chocolateList) {
        this.context = context;
        this.chocolateList = chocolateList;
        this.chocolateListFull = new ArrayList<>(chocolateList); // Salin senarai asal
    }

    @NonNull
    @Override
    public ChocolateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_choclates, parent, false);
        return new ChocolateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChocolateViewHolder holder, int position) {
        String chocolateName = chocolateList.get(position);
        holder.chocolateNameTextView.setText(chocolateName);

        holder.itemView.setOnClickListener(v -> {
            // Logik untuk membuka aktiviti baru berdasarkan nama coklat
            if (chocolateName.equalsIgnoreCase("KitKat")) {
                // Ganti KitKatActivity.class dengan aktiviti sebenar anda
                // Intent intent = new Intent(context, KitKatActivity.class);
                // context.startActivity(intent);
                android.widget.Toast.makeText(context, "Membuka halaman " + chocolateName, android.widget.Toast.LENGTH_SHORT).show();
            } else if (chocolateName.equalsIgnoreCase("Cadbury")) {
                // Ganti dengan aktiviti sebenar anda
                android.widget.Toast.makeText(context, "Membuka halaman " + chocolateName, android.widget.Toast.LENGTH_SHORT).show();
            }
            // Tambah 'else if' lain untuk coklat yang berbeza
        });
    }

    @Override
    public int getItemCount() {
        return chocolateList.size();
    }

    // Fungsi carian
    @Override
    public Filter getFilter() {
        return chocolateFilter;
    }

    private Filter chocolateFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(chocolateListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (String item : chocolateListFull) {
                    if (item.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            chocolateList.clear();
            chocolateList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    // ViewHolder
    public static class ChocolateViewHolder extends RecyclerView.ViewHolder {
        TextView chocolateNameTextView;

        public ChocolateViewHolder(@NonNull View itemView) {
            super(itemView);
            chocolateNameTextView = itemView.findViewById(R.id.textViewChocolateName);
        }
    }
}
