// Fail: InsertBarcodeActivity.java
package com.example.barcodescannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

public class InsertBarcodeActivity extends AppCompatActivity {

    // Komponen UI
    private EditText barcodeEditText;
    private Button barcodeSearchButton;
    private MaterialCardView resultCard;
    private TextView resultProductName, resultBarcodeNumber, resultStatusText;
    private ImageView resultStatusIcon;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_barcode);

        // Sambungkan komponen dari XML
        barcodeEditText = findViewById(R.id.barcodeEditText);
        barcodeSearchButton = findViewById(R.id.barcodeSearchButton);
        resultCard = findViewById(R.id.resultCard);
        resultProductName = findViewById(R.id.resultProductName);
        resultBarcodeNumber = findViewById(R.id.resultBarcodeNumber);
        resultStatusText = findViewById(R.id.resultStatusText);
        resultStatusIcon = findViewById(R.id.resultStatusIcon);

        bottomNavigationView = findViewById(R.id.bottom);

        // Fungsi untuk butang kembali
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Fungsi untuk butang carian
        barcodeSearchButton.setOnClickListener(v -> {
            String barcode = barcodeEditText.getText().toString().trim();
            if (!barcode.isEmpty()) {
                searchBarcode(barcode);
            }
        });

        // ====================== AKTIFKAN SEMUA NAVIGASI BAWAH ======================
        if (bottomNavigationView != null) {
            // Kita tidak set selected item di sini supaya keyboard/input tidak terganggu secara visual,
            // atau anda boleh set ke item yang tiada (default).
            
            bottomNavigationView.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    return true;
                } else if (itemId == R.id.nav_history) {
                    startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    return true;
                } else if (itemId == R.id.nav_bookmarks) {
                    startActivity(new Intent(getApplicationContext(), BookmarkActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    return true;
                } else if (itemId == R.id.nav_resources) {
                    startActivity(new Intent(getApplicationContext(), ResourcesActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    return true;
                }
                return false;
            });
        }
    }

    private void searchBarcode(String barcode) {
        if (barcode.equals("66263723873982")) {
            String productName = "PEPSI 250 ml";
            String fullProductName = productName + " (" + barcode + ")";
            resultProductName.setText(fullProductName);
            resultBarcodeNumber.setText(barcode);
            resultStatusIcon.setImageResource(R.drawable.icon_boycott);
            resultStatusText.setText("This product appears in public boycott lists!");
            resultStatusText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
            resultCard.setVisibility(View.VISIBLE);
        } else if (barcode.equals("54458221456")) {
            String productName = "SUPER RING";
            String fullProductName = productName + " (" + barcode + ")";
            resultProductName.setText(fullProductName);
            resultBarcodeNumber.setText(barcode);
            resultStatusIcon.setImageResource(R.drawable.icon_safe);
            resultStatusText.setText("Good news! This item is not flagged in any public boycott lists.");
            resultStatusText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
            resultCard.setVisibility(View.VISIBLE);
        } else if (barcode.equals("12345678910")) {
            String productName = "KITKAT";
            String fullProductName = productName + " (" + barcode + ")";
            resultProductName.setText(fullProductName);
            resultBarcodeNumber.setText(barcode);
            resultStatusIcon.setImageResource(R.drawable.icon_boycott);
            resultStatusText.setText("This product appears in public boycott lists!");
            resultStatusText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
            resultCard.setVisibility(View.VISIBLE);
        } else if (barcode.equals("987654321")) {
            String productName = "MAGGIE";
            String fullProductName = productName + " (" + barcode + ")";
            resultProductName.setText(fullProductName);
            resultBarcodeNumber.setText(barcode);
            resultStatusIcon.setImageResource(R.drawable.icon_boycott);
            resultStatusText.setText("This product appears in public boycott lists!");
            resultStatusText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
            resultCard.setVisibility(View.VISIBLE);
        } else if (barcode.equals("246810121416")) {
            String productName = "VICO";
            String fullProductName = productName + " (" + barcode + ")";
            resultProductName.setText(fullProductName);
            resultBarcodeNumber.setText(barcode);
            resultStatusIcon.setImageResource(R.drawable.icon_safe);
            resultStatusText.setText("Good news! This item is not flagged in any public boycott lists.");
            resultStatusText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
            resultCard.setVisibility(View.VISIBLE);
        } else {
            resultCard.setVisibility(View.GONE);
            Toast.makeText(this, "Barcode not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack(View view) {
        finish();
    }
}
