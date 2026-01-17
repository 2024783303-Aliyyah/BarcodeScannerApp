package com.example.barcodescannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RestaurantActivity extends AppCompatActivity {

    private EditText searchEditText;
    private TextView starbucksTextView, mcdonaldsTextView, dominosTextView;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        // Inisialisasi View dengan ID yang tepat dari XML
        ImageView backButton = findViewById(R.id.btnBack);
        searchEditText = findViewById(R.id.searchEditText);
        starbucksTextView = findViewById(R.id.restaurant_starbucks);
        mcdonaldsTextView = findViewById(R.id.restaurant_mcdonalds);
        dominosTextView = findViewById(R.id.restaurant_dominos);
        bottomNavigationView = findViewById(R.id.bottom);

        // Button Back
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Setup Klik Item
        setupClickListeners();
        setupNavigation();

        // Logik Carian
        if (searchEditText != null) {
            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    filterList(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }
    }

    private void setupClickListeners() {
        if (starbucksTextView != null) {
            starbucksTextView.setOnClickListener(v ->
                    startActivity(new Intent(this, StarbucksActivity.class)));
        }

        if (mcdonaldsTextView != null) {
            mcdonaldsTextView.setOnClickListener(v ->
                    startActivity(new Intent(this, McDonaldsActivity.class)));
        }

        if (dominosTextView != null) {
            dominosTextView.setOnClickListener(v ->
                    startActivity(new Intent(this, DominosActivity.class)));
        }
    }

    private void setupNavigation() {
        if (bottomNavigationView != null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
            bottomNavigationView.setOnItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    startActivity(new Intent(this, HomepageActivity.class));
                    return true;
                } else if (id == R.id.nav_history) {
                    startActivity(new Intent(this, HistoryActivity.class));
                    return true;
                } else if (id == R.id.nav_bookmarks) {
                    startActivity(new Intent(this, BookmarkActivity.class));
                    return true;
                } else if (id == R.id.nav_resources) {
                    startActivity(new Intent(this, ResourcesActivity.class));
                    return true;
                }
                return false;
            });
        }
    }

    private void filterList(String searchText) {
        String query = searchText.toLowerCase().trim();
        filterItem(starbucksTextView, "starbucks", query);
        filterItem(mcdonaldsTextView, "mcdonalds mcd", query);
        filterItem(dominosTextView, "dominos pizza domino", query);
    }

    private void filterItem(TextView textView, String itemName, String query) {
        if (textView != null) {
            textView.setVisibility(itemName.contains(query) ? View.VISIBLE : View.GONE);
        }
    }
}
