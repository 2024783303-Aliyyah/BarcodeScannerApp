package com.example.barcodescannerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchProductActivity extends AppCompatActivity {

    private EditText searchEditText;
    private ConstraintLayout productDetailsContainer;
    private ImageView productImage;
    private TextView productName, productDetails;
    private HistoryManager historyManager;
    private BookmarkManager bookmarkManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Standard for Android 15/16
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
        productName = findViewById(R.id.product_name);
        productDetails = findViewById(R.id.product_details);

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

    private void searchForProduct(String query) {
        if (query.isEmpty()) return;

        boolean found = false;
        String details = "";
        String status = "";

        if (query.equalsIgnoreCase("maggie")) {
            productImage.setImageResource(R.drawable.maggie);
            productName.setText("MAGGIE");
            details = "Company: Nestlé\nCategory: Food\nOrigin: Switzerland\n";
            status = "<font color='#FF0000'><b>BOYCOTT!</b></font>";
            found = true;
        } else if (query.equalsIgnoreCase("vico")) {
            productImage.setImageResource(R.drawable.vico);
            productName.setText("VICO");
            details = "Company: Maestro Swiss\nCategory: Drink\nOrigin: Malaysia\n";
            status = "<font color='#4CAF50'><b>SAFE / SUPPORT</b></font>";
            found = true;
        }

        if (found) {
            historyManager.addToHistory(query.toUpperCase());
            productDetails.setText(Html.fromHtml(details + "Status: " + status, Html.FROM_HTML_MODE_LEGACY));
            productDetailsContainer.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Product not found!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupNavigation() {
        BottomNavigationView nav = findViewById(R.id.bottom);
        nav.setSelectedItemId(R.id.nav_home); // Assuming this is the search activity
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_history) {
                startActivity(new Intent(this, HistoryActivity.class));
                return true;
            } else if (id == R.id.nav_bookmarks) {
                startActivity(new Intent(this, BookmarkActivity.class));
                return true;
            }
            return false;
        });
    }

    private void handleIntent(Intent intent) {
        if (intent == null) return;

        String productNameFromHistory = null;

        // Check if the intent has the extra from our HistoryAdapter
        if (intent.hasExtra("PRODUCT_NAME")) {
            productNameFromHistory = intent.getStringExtra("PRODUCT_NAME");
        }
        // Also check for the one from bookmarks
        else if (intent.hasExtra("PRODUCT_NAME_FROM_BOOKMARK")) {
            productNameFromHistory = intent.getStringExtra("PRODUCT_NAME_FROM_BOOKMARK");
        }

        // If we received a product name from either History or Bookmarks...
        if (productNameFromHistory != null) {
            searchEditText.setText(productNameFromHistory); // Put the name in the search bar
            searchForProduct(productNameFromHistory);
        }
    }

    public void goBack(View view) { finish(); }
}