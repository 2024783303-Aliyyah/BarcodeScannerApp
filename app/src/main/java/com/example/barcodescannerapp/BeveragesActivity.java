package com.example.barcodescannerapp; // Pastikan nama pakej anda betul

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BeveragesActivity extends AppCompatActivity {

    private EditText searchEditText;
    private TextView fantaTextView, pepsiTextView, spriteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages);

        ImageView backButton = findViewById(R.id.btnBack);
        searchEditText = findViewById(R.id.searchEditText);
        fantaTextView = findViewById(R.id.beverages_fanta);
        pepsiTextView = findViewById(R.id.beverages_pepsi);
        spriteTextView = findViewById(R.id.beverages_sprite);

        backButton.setOnClickListener(v -> finish());

        setupClickListeners();

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

    /**
     * Menetapkan OnClickListener untuk setiap item coklat.
     * Setiap listener kini membuka halaman butiran produk yang spesifik.
     */
    private void setupClickListeners() {

        fantaTextView.setOnClickListener(v -> {
            Intent intent = new Intent(BeveragesActivity.this, FantaActivity.class);
            startActivity(intent);
        });

        pepsiTextView.setOnClickListener(v -> {
            // Pastikan nama kelas aktiviti anda betul, contoh: CadburyActivity atau CadburryActivity
            Intent intent = new Intent(BeveragesActivity.this, PepsiActivity.class);
            startActivity(intent);
        });

        spriteTextView.setOnClickListener(v -> {
            Intent intent = new Intent(BeveragesActivity.this, SpriteActivity.class);
            startActivity(intent);
        });

    }

    /**
     * Menapis senarai berdasarkan input carian pengguna.
     * @param searchText Teks yang ditaip oleh pengguna.
     */
    private void filterList(String searchText) {
        String query = searchText.toLowerCase().trim();
        filterItem(fantaTextView, "fanta", query);
        filterItem(pepsiTextView, "pepsi", query);
        filterItem(spriteTextView, "sprite", query);

    }

    /**
     * Fungsi bantuan untuk menunjukkan atau menyembunyikan satu item.
     * @param textView Komponen TextView yang hendak ditapis.
     * @param itemName Nama item dalam huruf kecil.
     * @param query Teks carian dalam huruf kecil.
     */
    private void filterItem(TextView textView, String itemName, String query) {
        if (itemName.contains(query)) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    // Fungsi openProductDetail() telah dibuang kerana ia tidak lagi diperlukan
    // dan digantikan dengan Intent yang spesifik dalam setupClickListeners().
}
