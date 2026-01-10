package com.example.barcodescannerapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ScanProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scanproduct);

        // Button Back -> Ke ScanProductCamera
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainScanProductActivity.class);
            startActivity(intent);
            finish();
        });

        // Button Exit -> Ke MainScanProductActivity
        findViewById(R.id.btnExit).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainScanProductActivity.class);
            startActivity(intent);
            finish();
        });

        // Button Scan -> Untuk demo (kembali ke scanner)
        findViewById(R.id.btnScan).setOnClickListener(v -> {
            Intent intent = new Intent(this, ScanProductCamera.class);
            startActivity(intent);
            finish();
        });
    }
}