package com.example.barcodescannerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DominosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dominos);
        
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Cari komponen UI
        ImageView backButton = findViewById(R.id.btnBack);
        Button continueButton = findViewById(R.id.btnContinue);

        // Aksi untuk kembali (tutup halaman)
        View.OnClickListener backAction = v -> finish();

        // Set klik listener
        if (backButton != null) backButton.setOnClickListener(backAction);
        if (continueButton != null) continueButton.setOnClickListener(backAction);
    }
}
