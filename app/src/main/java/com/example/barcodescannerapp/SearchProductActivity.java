// Fail: SearchProductActivity.java
package com.example.barcodescannerapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SearchProductActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchButton;
    private ConstraintLayout productDetailsContainer;
    private ImageView productImage;
    private TextView productName;
    private TextView productDetails;
    private Button continueButton;
    private ImageView bookmarkButton;
    private BottomNavigationView bottomNavigationView;

    private BookmarkManager bookmarkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        // Inisialisasi semua view
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        productDetailsContainer = findViewById(R.id.product_details_container);
        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        productDetails = findViewById(R.id.product_details);
        continueButton = findViewById(R.id.continue_button);
        bookmarkButton = findViewById(R.id.bookmark_button);
        bottomNavigationView = findViewById(R.id.bottom);

        bookmarkManager = new BookmarkManager(this);

        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString().trim();
            searchForProduct(query);
        });

        continueButton.setOnClickListener(v -> {
            productDetailsContainer.setVisibility(View.GONE);
            searchEditText.setText("");
        });

        bookmarkButton.setOnClickListener(v -> {
            String currentProduct = productName.getText().toString();
            if (!currentProduct.isEmpty()) {
                bookmarkManager.addBookmark(currentProduct);
                Toast.makeText(this, currentProduct + " saved to Bookmarks!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No product displayed to bookmark.", Toast.LENGTH_SHORT).show();
            }
        });

        // ====================== PEMBETULAN UTAMA BERMULA DI SINI ======================
        // Panggil fungsi untuk mengendalikan Intent semasa aktiviti dicipta
        handleIntent(getIntent());
        // ==========================================================================

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
            } else if (itemId == R.id.nav_history) {
                Toast.makeText(SearchProductActivity.this, "History page coming soon!", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.nav_bookmarks) {
                startActivity(new Intent(getApplicationContext(), BookmarkActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
            } else if (itemId == R.id.nav_resources) {
                Toast.makeText(SearchProductActivity.this, "Resources page coming soon!", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    // ====================== TAMBAH FUNGSI onNewIntent() ======================
    /**
     * Fungsi ini akan dipanggil jika SearchProductActivity sudah berjalan
     * dan menerima Intent baharu (contohnya, dari BookmarkActivity).
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent); // Kemas kini intent aktiviti dengan yang baharu
        // Panggil fungsi untuk mengendalikan Intent
        handleIntent(intent);
    }
    // =====================================================================

    // ====================== BUAT FUNGSI KHAS UNTUK KENDALI INTENT ======================
    /**
     * Fungsi ini menyemak jika ada data produk dari bookmark dan memaparkannya.
     * @param intent Intent yang diterima oleh aktiviti.
     */
    private void handleIntent(Intent intent) {
        if (intent != null && intent.hasExtra("PRODUCT_NAME_FROM_BOOKMARK")) {
            String productNameFromBookmark = intent.getStringExtra("PRODUCT_NAME_FROM_BOOKMARK");
            if (productNameFromBookmark != null) {
                searchEditText.setText(productNameFromBookmark);
                searchForProduct(productNameFromBookmark);
            }
        }
    }
    // ===============================================================================

    private void searchForProduct(String productNameQuery) {
        if (productNameQuery.equalsIgnoreCase("maggie")) {
            productImage.setImageResource(R.drawable.maggie);
            productName.setText("MAGGIE");
            String details = "Product Name: Maggi\n" +
                    "Company: Nestlé\n" +
                    "Category: Food & Instant Noodles\n" +
                    "Country of Origin: Switzerland (Nestlé HQ)\n";
            String statusText = "Status : <font color='#FF0000'>BOYCOTT!</font>";
            String fullDetailsText = details + statusText;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                productDetails.setText(Html.fromHtml(fullDetailsText, Html.FROM_HTML_MODE_LEGACY));
            } else {
                productDetails.setText(Html.fromHtml(fullDetailsText));
            }

            productDetailsContainer.setVisibility(View.VISIBLE);

        } else if (productNameQuery.equalsIgnoreCase("kitkat")) {
            productImage.setImageResource(R.drawable.kitkat);
            productName.setText("KITKAT");
            String details = "Product Name: KitKat\n" +
                    "Company: Nestlé\n" +
                    "Category: Food & Chocolate\n" +
                    "Country of Origin: UK\n";
            String statusText = "Status : <font color='#FF0000'>BOYCOTT!</font>";
            String fullDetailsText = details + statusText;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                productDetails.setText(Html.fromHtml(fullDetailsText, Html.FROM_HTML_MODE_LEGACY));
            } else {
                productDetails.setText(Html.fromHtml(fullDetailsText));
            }

            productDetailsContainer.setVisibility(View.VISIBLE);

        }
        else if (productNameQuery.equalsIgnoreCase("vico")) {
            productImage.setImageResource(R.drawable.vico);
            productName.setText("VICO");
            String details = "Product Name: Vico\n" +
                    "Company: Maestro Swiss\n" +
                    "Category: Drink & Chocolate\n" +
                    "Country of Origin: Malaysia\n";
            String statusText = "Status : <font color='#8fce00'>BOYCOTT!</font>";
            String fullDetailsText = details + statusText;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                productDetails.setText(Html.fromHtml(fullDetailsText, Html.FROM_HTML_MODE_LEGACY));
            } else {
                productDetails.setText(Html.fromHtml(fullDetailsText));
            }

            productDetailsContainer.setVisibility(View.VISIBLE);

        }else {
            Toast.makeText(this, "Product not found!", Toast.LENGTH_SHORT).show();
            productDetailsContainer.setVisibility(View.GONE);
        }
    }

    public void goBack(View view) {
        finish();
    }
}
