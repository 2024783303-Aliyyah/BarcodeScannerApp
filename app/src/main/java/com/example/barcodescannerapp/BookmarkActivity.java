// Fail: BookmarkActivity.java
package com.example.barcodescannerapp;

import android.content.Context; // Import Context
import android.content.Intent; // Import Intent
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {

    private RecyclerView bookmarksRecyclerView;
    private BookmarkAdapter adapter;
    private ArrayList<String> bookmarkedItems;
    private EditText bookmarkSearchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        bookmarksRecyclerView = findViewById(R.id.bookmarksRecyclerView);
        bookmarkSearchEditText = findViewById(R.id.bookmarkSearchEditText);
        bookmarksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadBookmarkedItems();

        adapter = new BookmarkAdapter(bookmarkedItems);
        bookmarksRecyclerView.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        bookmarkSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void loadBookmarkedItems() {
        bookmarkedItems = new ArrayList<>();
        // Data dummy ini perlu digantikan dengan data sebenar dari SharedPreferences atau Database
        bookmarkedItems.add("Maggie"); // Gunakan nama yang konsisten dengan logik carian
        bookmarkedItems.add("KitKat");
        bookmarkedItems.add("Milo Activ-Go");
        bookmarkedItems.add("Nescafe Classic");
        bookmarkedItems.add("Gardenia Original Classic");
        bookmarkedItems.add("Coca-Cola");
    }
}

// ====================== KELAS ADAPTER (DENGAN FUNGSI KLIK) ======================
class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> implements Filterable {

    private ArrayList<String> items;
    private ArrayList<String> itemsFull;

    public BookmarkAdapter(ArrayList<String> items) {
        this.items = items;
        this.itemsFull = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bookmark, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        String productName = items.get(position);
        holder.productNameTextView.setText(productName);

        // ====================== START OF FIX ======================
        // Tambah OnClickListener pada setiap item dalam senarai
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Dapatkan konteks (context) dari view yang diklik
                Context context = v.getContext();

                // 2. Cipta Intent untuk pergi ke SearchProductActivity
                Intent intent = new Intent(context, SearchProductActivity.class);

                // 3. Hantar nama produk yang diklik sebagai data tambahan
                intent.putExtra("PRODUCT_NAME_FROM_BOOKMARK", productName);

                // 4. Mulakan aktiviti
                context.startActivity(intent);
            }
        });
        // ======================= END OF FIX =======================
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Filter getFilter() {
        return bookmarkFilter;
    }

    private Filter bookmarkFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(itemsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (String item : itemsFull) {
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
            items.clear();
            items.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    static class BookmarkViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView;
        public BookmarkViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.bookmarked_product_name);
        }
    }
}
