package com.example.barcodescannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainScanProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainscanproduct);

        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Button Back
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Button Scan Product - Panggil dialog lakonan
        findViewById(R.id.cardScan).setOnClickListener(v -> showCameraPermissionDialog());

        // Button Insert Barcode
        findViewById(R.id.cardInsert).setOnClickListener(v ->
                Toast.makeText(this, "Insert Barcode clicked", Toast.LENGTH_SHORT).show()
        );
    }

    // Dialog lakonan (Fake Permission)
    private void showCameraPermissionDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Permission Required")
                .setMessage("This app needs access to your camera to scan barcodes. Allow permission?")
                .setPositiveButton("Allow", (dialog, which) -> {
                    // Tekan Allow, terus "lompat" ke page kamera
                    openScanner();
                })
                .setNegativeButton("Deny", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void openScanner() {
        Intent intent = new Intent(MainScanProductActivity.this, ScanProductCamera.class);
        startActivity(intent);
    }
}