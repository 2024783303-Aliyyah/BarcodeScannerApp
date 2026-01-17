// Fail: BookmarkActivity.java
package com.example.barcodescannerapp;

import android.content.Context;
import android.content.Intent;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookmarkActivity extends AppCompatActivity {

    private RecyclerView bookmarksRecyclerView;
    private BookmarkAdapter adapter;
    private ArrayList<String> bookmarkedItems;
    private EditText bookmarkSearchEditText;

    private BookmarkManager bookmarkManager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        bookmarksRecyclerView = findViewById(R.id.bookmarksRecyclerView);
        bookmarkSearchEditText = findViewById(R.id.bookmarkSearchEditText);
        bottomNavigationView = findViewById(R.id.bottom);
        bookmarksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookmarkManager = new BookmarkManager(this);

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

        // ====================== AKTIFKAN SEMUA NAVIGASI BAWAH ======================
        bottomNavigationView.setSelectedItemId(R.id.nav_bookmarks); 
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
            } else if (itemId == R.id.nav_history) {
                startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
            } else if (itemId == R.id.nav_bookmarks) {
                return true;
            } else if (itemId == R.id.nav_resources) {
                startActivity(new Intent(getApplicationContext(), ResourcesActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
            }
            return false;
        });
    }

    public void goBack(View view) {
        finish();
    }

    private void loadBookmarkedItems() {
        Set<String> bookmarksSet = bookmarkManager.getBookmarks();
        bookmarkedItems = new ArrayList<>(bookmarksSet);
    }
}

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

        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, SearchProductActivity.class);
            intent.putExtra("PRODUCT_NAME_FROM_BOOKMARK", productName);
            context.startActivity(intent);
        });
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
