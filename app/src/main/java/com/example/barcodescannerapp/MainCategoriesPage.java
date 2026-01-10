package com.example.barcodescannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.card.MaterialCardView;

public class MainCategoriesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_categories);

        // Boleh kekalkan kod padding sistem ini
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Fungsi untuk butang kembali (back button)
        ImageView backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(v -> finish());


        // ========== BAHAGIAN YANG DIUBAH SUAI & DIBETULKAN ==========

        // 1. BUANG kod yang menyebabkan loop tidak terhingga.
        // startActivity(new Intent(MainCategoriesPage.this, MainCategoriesPage.class)); // BARIS INI TELAH DIBUANG

        // 2. Cari kad 'Chocolates' dari fail layout menggunakan ID-nya.
        //    (Pastikan dalam activity_main_categories.xml, kad coklat mempunyai android:id="@+id/cardChocolates")
        MaterialCardView chocolatesCard = findViewById(R.id.cardChocolates);

        // 3. Tetapkan OnClickListener untuk membuka ChoclatesActivity.
        chocolatesCard.setOnClickListener(v -> {
            // Cipta Intent untuk membuka ChoclatesActivity.
            Intent intent = new Intent(MainCategoriesPage.this, ChoclatesActivity.class);

            // Mulakan aktiviti baru (buka halaman Choclates).
            startActivity(intent);
        });

        // Anda juga boleh tambah listener untuk kad lain di sini. Contoh:
        // MaterialCardView beveragesCard = findViewById(R.id.cardBeverages);
        // beveragesCard.setOnClickListener(v -> { ... });
    }
}
