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

public class ChoclatesActivity extends AppCompatActivity {

    private EditText searchEditText;
    private TextView kitkatTextView, cadburyTextView, tobleroneTextView, ferreroTextView, kinderTextView, mandmsTextView, snickersTextView;

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

        kitkatTextView.setOnClickListener(v -> {
            Intent intent = new Intent(ChoclatesActivity.this, KitkatActivity.class);
            startActivity(intent);
        });

        cadburyTextView.setOnClickListener(v -> {
            // Pastikan nama kelas aktiviti anda betul, contoh: CadburyActivity atau CadburryActivity
            Intent intent = new Intent(ChoclatesActivity.this, CadburryActivity.class);
            startActivity(intent);
        });

        tobleroneTextView.setOnClickListener(v -> {
            Intent intent = new Intent(ChoclatesActivity.this, TobleroneActivity.class);
            startActivity(intent);
        });

        ferreroTextView.setOnClickListener(v -> {
            Intent intent = new Intent(ChoclatesActivity.this, FerreroActivity.class);
            startActivity(intent);
        });

        kinderTextView.setOnClickListener(v -> {
            Intent intent = new Intent(ChoclatesActivity.this, KinderBuenoActivity.class);
            startActivity(intent);
        });

        mandmsTextView.setOnClickListener(v -> {
            Intent intent = new Intent(ChoclatesActivity.this, MNMActivity.class);
            startActivity(intent);
        });

        snickersTextView.setOnClickListener(v -> {
            Intent intent = new Intent(ChoclatesActivity.this, SnickersActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Menapis senarai berdasarkan input carian pengguna.
     * @param searchText Teks yang ditaip oleh pengguna.
     */
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
