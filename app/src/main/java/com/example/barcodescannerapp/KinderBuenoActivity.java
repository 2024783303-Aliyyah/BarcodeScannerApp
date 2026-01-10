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

public class KinderBuenoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sambungkan fail Java ini ke fail reka bentuknya
        setContentView(R.layout.activity_kinder_bueno);

        // Cari komponen UI dari fail reka bentuk
        ImageView backButton = findViewById(R.id.btnBack);
        Button continueButton = findViewById(R.id.btnContinue);

        // Cipta satu 'action' untuk kembali ke halaman sebelumnya
        View.OnClickListener backAction = v -> {
            // 'finish()' ialah arahan untuk menutup halaman semasa
            finish();
        };

        // Tetapkan 'action' tersebut pada kedua-dua butang
        backButton.setOnClickListener(backAction);
        continueButton.setOnClickListener(backAction);
    }
}
