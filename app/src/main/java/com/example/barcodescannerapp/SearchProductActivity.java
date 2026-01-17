// Fail: SearchProductActivity.java
package com.example.barcodescannerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color; // <-- IMPORT BARU DITAMBAH DI SINI
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchProductActivity extends AppCompatActivity {

    private EditText searchEditText;
    private ConstraintLayout productDetailsContainer;
    private ImageView productImage;
    private TextView productName; // Ini untuk tajuk besar "MAGGI"

    // Pemboleh ubah untuk TextViews di dalam GridLayout
    private TextView detailProductName, detailCompany, detailCategory, detailCountry, detailStatus;

    private HistoryManager historyManager;
    private BookmarkManager bookmarkManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Standard untuk Android 15/16
        setContentView(R.layout.activity_search_product);

        searchEditText = findViewById(R.id.searchEditText);
        // Apply Insets for modern full-screen displays
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        handleIntent(getIntent());
    }

    private void initViews() {

        productDetailsContainer = findViewById(R.id.product_details_container);
        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name); // Tajuk utama

        detailProductName = findViewById(R.id.detail_product_name);
        detailCompany = findViewById(R.id.detail_company);
        detailCategory = findViewById(R.id.detail_category);
        detailCountry = findViewById(R.id.detail_country);
        detailStatus = findViewById(R.id.detail_status);

        historyManager = new HistoryManager(this);
        bookmarkManager = new BookmarkManager(this);

        findViewById(R.id.searchButton).setOnClickListener(v -> searchForProduct(searchEditText.getText().toString().trim()));
        findViewById(R.id.continue_button).setOnClickListener(v -> productDetailsContainer.setVisibility(View.GONE));

        findViewById(R.id.bookmark_button).setOnClickListener(v -> {
            String name = productName.getText().toString();
            if (!name.isEmpty()) {
                bookmarkManager.addBookmark(name);
                Toast.makeText(this, "Saved to Bookmarks", Toast.LENGTH_SHORT).show();
            }
        });

        setupNavigation();
    }

    // ====================== FUNGSI searchForProduct() DENGAN KOD WARNA TERUS ======================
    private void searchForProduct(String query) {
        if (query.isEmpty()) return;

        boolean found = false;

        // Logik untuk "maggie"
        if (query.equalsIgnoreCase("maggie")) {
            productImage.setImageResource(R.drawable.maggie);
            productName.setText("MAGGIE"); // Set tajuk besar

            // Set butiran dalam GridLayout
            detailProductName.setText("Maggie");
            detailCompany.setText("Nestlé");
            detailCategory.setText("Food & Instant Noodles");
            detailCountry.setText("Switzerland");

            // Set status dengan warna
            detailStatus.setText("BOYCOTT!");
            detailStatus.setTextColor(Color.RED); // DIUBAH: Guna kod warna terus

            found = true;
        }
        // Logik untuk produk lain
        else if (query.equalsIgnoreCase("vico")) {
            productImage.setImageResource(R.drawable.vico);
            productName.setText("VICO");

            detailProductName.setText("Vico");
            detailCompany.setText("Maestro Swiss");
            detailCategory.setText("Drink");
            detailCountry.setText("Malaysia");
            detailStatus.setText("SAFE / SUPPORT");
            detailStatus.setTextColor(Color.parseColor("#4CAF50")); // DIUBAH: Guna kod warna hijau yang lebih cantik

            found = true;
        }
        else if (query.equalsIgnoreCase("kitkat")) {
            productImage.setImageResource(R.drawable.kitkat);
            productName.setText("KITKAT");

            detailProductName.setText("KitKat");
            detailCompany.setText("Nestlé");
            detailCategory.setText("Chocolate");
            detailCountry.setText("United Kingdom");
            detailStatus.setText("BOYCOTT!");
            detailStatus.setTextColor(Color.RED); // DIUBAH: Guna kod warna terus

            found = true;
        }
        else if (query.equalsIgnoreCase("pepsi")) {
            productImage.setImageResource(R.drawable.pepsi);
            productName.setText("PEPSI");

            detailProductName.setText("Pepsi");
            detailCompany.setText("PepsiCo (Pepsi's flagship product");
            detailCategory.setText("Carbonated cola soft drink");
            detailCountry.setText("United State(Originally developed North Caronila in 1893)");
            detailStatus.setText("BOYCOTT!");
            detailStatus.setTextColor(Color.RED); // DIUBAH: Guna kod warna terus

            found = true;
        }
        else if (query.equalsIgnoreCase("fanta")) {
            productImage.setImageResource(R.drawable.fanta);
            productName.setText("Fanta");

            detailProductName.setText("Fanta");
            detailCompany.setText("The Coca-Cola Company (brand a manufacturer)");
            detailCategory.setText("Carbonated soft drink");
            detailCountry.setText("Coca-Cola Deutschland");
            detailStatus.setText("BOYCOTT!");
            detailStatus.setTextColor(Color.RED); // DIUBAH: Guna kod warna terus

            found = true;
        }



        // Tunjukkan atau sembunyikan kad berdasarkan hasil carian
        if (found) {
            historyManager.addToHistory(query.toUpperCase());
            productDetailsContainer.setVisibility(View.VISIBLE); // Tunjukkan kad
        } else {
            Toast.makeText(this, "Product not found!", Toast.LENGTH_SHORT).show();
            productDetailsContainer.setVisibility(View.GONE); // Sembunyikan jika tidak jumpa
        }
    }
    // ===============================================================================

    private void setupNavigation() {
        BottomNavigationView nav = findViewById(R.id.bottom);
        // Tidak perlu setSelectedItemId di sini jika ini bukan halaman utama
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_history) {
                startActivity(new Intent(this, HistoryActivity.class));
                return true;
            } else if (id == R.id.nav_bookmarks) {
                startActivity(new Intent(this, BookmarkActivity.class));
                return true;
            }
            else if (id == R.id.nav_resources){
                startActivity(new Intent(this, ResourcesActivity.class));
                return true;
            }
            // Tambah ini untuk kembali ke Home
            else if (id == R.id.nav_home){
                startActivity(new Intent(this, HomepageActivity.class));
                return true;
            }
            return false;
        });
    }

    private void handleIntent(Intent intent) {
        if (intent == null) return;

        String productNameFromHistory = null;

        if (intent.hasExtra("PRODUCT_NAME")) {
            productNameFromHistory = intent.getStringExtra("PRODUCT_NAME");
        }
        else if (intent.hasExtra("PRODUCT_NAME_FROM_BOOKMARK")) {
            productNameFromHistory = intent.getStringExtra("PRODUCT_NAME_FROM_BOOKMARK");
        }

        if (productNameFromHistory != null) {
            searchEditText.setText(productNameFromHistory);
            searchForProduct(productNameFromHistory);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    public void goBack(View view) { finish(); }
}
