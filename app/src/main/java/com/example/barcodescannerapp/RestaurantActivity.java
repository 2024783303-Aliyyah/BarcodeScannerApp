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

public class RestaurantActivity extends AppCompatActivity {

    private EditText searchEditText;
    private TextView starbucksTextView, mcdonaldsTextView, dominosTextView;

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

        // Button Back
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Setup Klik Item
        setupClickListeners();

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