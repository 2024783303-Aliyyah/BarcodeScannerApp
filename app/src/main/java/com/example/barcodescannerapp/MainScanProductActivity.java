package com.example.barcodescannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

public class MainScanProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainscanproduct);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Back
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Scan Product → fake permission dialog
        findViewById(R.id.cardScan).setOnClickListener(v -> showFakePermissionDialog());

        // Insert Barcode
        MaterialCardView insertBarcodeCard = findViewById(R.id.cardInsert);
        if (insertBarcodeCard != null) {
            insertBarcodeCard.setOnClickListener(v -> openInsertBarcodeActivity(v));
        }

        // Panggil fungsi navigation
        setupNavigation();
    }

    public void openInsertBarcodeActivity(View view) {
        Intent intent = new Intent(this, InsertBarcodeActivity.class);
        startActivity(intent);
    }

    private void setupNavigation() {
        BottomNavigationView nav = findViewById(R.id.bottom);
        if (nav != null) {
            // Set item yang sedang aktif (contoh: Home)
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

    private void showFakePermissionDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Camera Permission")
                .setMessage("This app needs access to your camera to scan barcodes. Allow permission?")
                .setCancelable(false)
                .setPositiveButton("Allow", (dialog, which) -> {
                    startActivity(new Intent(MainScanProductActivity.this, ScanProductCamera.class));
                })
                .setNegativeButton("Deny", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
