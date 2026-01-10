package com.example.barcodescannerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MNMActivity extends AppCompatActivity {

    // 1. Kod mesti berada di dalam fungsi 'onCreate'
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 2. Baris 'super.onCreate' mesti berada di sini, di baris pertama
        super.onCreate(savedInstanceState);

        // Sambungkan fail Java ini ke fail reka bentuknya
        // Pastikan nama fail layout adalah betul (cth: R.layout.activity_mnm)
        setContentView(R.layout.activity_mnmactivity);

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
    // 3. Pastikan kurungan tutup '}' untuk onCreate berada di sini
}
// 4. Dan kurungan tutup '}' untuk kelas berada di paling luar
