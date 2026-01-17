package com.example.barcodescannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

public class MainCategoriesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_categories);

        // Kod padding sistem
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Fungsi untuk butang kembali (back button)
        ImageView backButton = findViewById(R.id.btnBack);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Kad 'Chocolates'
        MaterialCardView chocolatesCard = findViewById(R.id.cardChocolates);
        if (chocolatesCard != null) {
            chocolatesCard.setOnClickListener(v -> {
                Intent intent = new Intent(MainCategoriesPage.this, ChoclatesActivity.class);
                startActivity(intent);
            });
        }

        // Kad 'Beverages'
        MaterialCardView beveragesCard = findViewById(R.id.cardBeverages);
        if (beveragesCard != null) {
            beveragesCard.setOnClickListener(v -> {
                Intent intent = new Intent(MainCategoriesPage.this, BeveragesActivity.class);
                startActivity(intent);
            });
        }

        // Kad 'Restaurants'
        MaterialCardView restaurantsCard = findViewById(R.id.cardRestaurants);
        if (restaurantsCard != null) {
            restaurantsCard.setOnClickListener(v -> {
                Intent intent = new Intent(MainCategoriesPage.this, RestaurantActivity.class);
                startActivity(intent);
            });
        }

        // Panggil fungsi navigation
        setupNavigation();
    }

    private void setupNavigation() {
        BottomNavigationView nav = findViewById(R.id.bottom);
        if (nav != null) {
            // Set item yang sedang aktif (Categories selalunya tiada item menu khusus, tapi boleh set Home)
            nav.setSelectedItemId(R.id.nav_home);

            nav.setOnItemSelectedListener(item -> {
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
}
