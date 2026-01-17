package com.example.barcodescannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ScanProductCamera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scan_product_camera);

        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Button Back & Close
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            Intent intent = new Intent(ScanProductCamera.this, MainScanProductActivity.class);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btnClose).setOnClickListener(v -> finish());

        // KLIK PADA GAMBAR BARCODE
        ImageView imgBarcode = findViewById(R.id.ivScannerPlaceholder);
        if (imgBarcode != null) {
            imgBarcode.setOnClickListener(v -> {
                Intent intent = new Intent(ScanProductCamera.this, ScanProductActivity.class);
                startActivity(intent);
            });
        }

        // Panggil fungsi navigation
        setupNavigation();
    }

    private void setupNavigation() {
        BottomNavigationView nav = findViewById(R.id.bottom);
        if (nav != null) {
            // Set item yang sedang aktif
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
                    // Pastikan anda mempunyai ResourcesActivity.java
                    startActivity(new Intent(this, ResourcesActivity.class));
                    return true;
                }
                return false;
            });
        }
    }
}
