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

        // 1. Kad 'Chocolates'
        MaterialCardView chocolatesCard = findViewById(R.id.cardChocolates);
        chocolatesCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainCategoriesPage.this, ChoclatesActivity.class);
            startActivity(intent);
        });

        // 2. Kad 'Beverages'
        MaterialCardView beveragesCard = findViewById(R.id.cardBeverages);
        beveragesCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainCategoriesPage.this, BeveragesActivity.class);
            startActivity(intent);
        });

        // 3. Kad 'Restaurants'
        MaterialCardView restaurantsCard = findViewById(R.id.cardRestaurants);
        restaurantsCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainCategoriesPage.this, RestaurantActivity.class);
            startActivity(intent);
        });
    }
}
