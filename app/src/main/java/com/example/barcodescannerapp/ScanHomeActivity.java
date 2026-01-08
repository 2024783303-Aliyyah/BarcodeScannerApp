package com.example.barcodescannerapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ScanHomeActivity extends AppCompatActivity {

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


        findViewById(R.id.btnBack).setOnClickListener(v -> finish());


        findViewById(R.id.cardScan).setOnClickListener(v ->
                Toast.makeText(this, "Scan Product clicked", Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.cardInsert).setOnClickListener(v ->
                Toast.makeText(this, "Insert Barcode clicked", Toast.LENGTH_SHORT).show()
        );
    }
}
