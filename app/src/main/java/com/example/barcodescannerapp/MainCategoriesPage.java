package com.example.barcodescannerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainCategoriesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // INI BARIS PALING PENTING: Memuatkan fail layout yang betul
        setContentView(R.layout.activity_main_categories);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Tambah fungsi untuk butang kembali (back button)
        ImageView backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tutup aktiviti semasa dan kembali ke halaman sebelumnya
                finish();
            }
        });

        // Di sini anda boleh tambah listener untuk cardChocolates, cardBeverages, dll.
        // Contoh:
        // findViewById(R.id.cardChocolates).setOnClickListener(v -> { ... });
    }
}

