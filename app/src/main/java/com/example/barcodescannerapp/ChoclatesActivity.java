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

public class ChoclatesActivity extends AppCompatActivity {

    private EditText searchEditText;
    private TextView kitkatTextView, cadburyTextView, tobleroneTextView, ferreroTextView, kinderTextView, mandmsTextView, snickersTextView;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choclates);

        ImageView backButton = findViewById(R.id.btnBack);
        searchEditText = findViewById(R.id.searchEditText);
        kitkatTextView = findViewById(R.id.chocolate_kitkat);
        cadburyTextView = findViewById(R.id.chocolate_cadbury);
        tobleroneTextView = findViewById(R.id.chocolate_toblerone);
        ferreroTextView = findViewById(R.id.chocolate_ferrero);
        kinderTextView = findViewById(R.id.chocolate_kinder);
        mandmsTextView = findViewById(R.id.chocolate_mnm);
        snickersTextView = findViewById(R.id.chocolate_snickers);
        bottomNavigationView = findViewById(R.id.bottom);

        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        setupClickListeners();
        setupNavigation();

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
        if (kitkatTextView != null) {
            kitkatTextView.setOnClickListener(v -> startActivity(new Intent(this, KitkatActivity.class)));
        }
        if (cadburyTextView != null) {
            cadburyTextView.setOnClickListener(v -> startActivity(new Intent(this, CadburryActivity.class)));
        }
        if (tobleroneTextView != null) {
            tobleroneTextView.setOnClickListener(v -> startActivity(new Intent(this, TobleroneActivity.class)));
        }
        if (ferreroTextView != null) {
            ferreroTextView.setOnClickListener(v -> startActivity(new Intent(this, FerreroActivity.class)));
        }
        if (kinderTextView != null) {
            kinderTextView.setOnClickListener(v -> startActivity(new Intent(this, KinderBuenoActivity.class)));
        }
        if (mandmsTextView != null) {
            mandmsTextView.setOnClickListener(v -> startActivity(new Intent(this, MNMActivity.class)));
        }
        if (snickersTextView != null) {
            snickersTextView.setOnClickListener(v -> startActivity(new Intent(this, SnickersActivity.class)));
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
        filterItem(kitkatTextView, "kitkat", query);
        filterItem(cadburyTextView, "cadbury", query);
        filterItem(tobleroneTextView, "toblerone", query);
        filterItem(ferreroTextView, "ferrero rocher", query);
        filterItem(kinderTextView, "kinder bueno", query);
        filterItem(mandmsTextView, "m and m's", query);
        filterItem(snickersTextView, "snickers", query);
    }

    private void filterItem(TextView textView, String itemName, String query) {
        if (textView != null) {
            textView.setVisibility(itemName.contains(query) ? View.VISIBLE : View.GONE);
        }
    }
}
