package com.example.barcodescannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
    }
}
