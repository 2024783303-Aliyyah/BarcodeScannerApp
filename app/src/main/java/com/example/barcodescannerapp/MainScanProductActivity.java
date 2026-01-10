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
        findViewById(R.id.cardInsert).setOnClickListener(v ->
                Toast.makeText(this, "Insert Barcode clicked", Toast.LENGTH_SHORT).show()
        );
    }

    private void showFakePermissionDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Camera Permission")
                .setMessage("Allow camera access to scan product?")
                .setCancelable(false)
                .setPositiveButton("Allow", (dialog, which) -> {
                    startActivity(
                            new Intent(MainScanProductActivity.this, ScanProductCamera.class)
                    );
                })
                .setNegativeButton("Deny", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
