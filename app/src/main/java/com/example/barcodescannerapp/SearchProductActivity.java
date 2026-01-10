// Fail: SearchProductActivity.java
package com.example.barcodescannerapp;

import android.content.Intent; // Import Intent
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Html;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SearchProductActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchButton;
    private ConstraintLayout productDetailsContainer;
    private ImageView productImage;
    private TextView productName;
    private TextView productDetails;
    private Button continueButton;
    private ImageView bookmarkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        productDetailsContainer = findViewById(R.id.product_details_container);
        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        productDetails = findViewById(R.id.product_details);
        continueButton = findViewById(R.id.continue_button);
        bookmarkButton = findViewById(R.id.bookmark_button);

        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString().trim();
            searchForProduct(query);
        });

        continueButton.setOnClickListener(v -> {
            productDetailsContainer.setVisibility(View.GONE);
            searchEditText.setText("");
        });

        bookmarkButton.setOnClickListener(v -> {
            Toast.makeText(this, "Product saved to Bookmarked!", Toast.LENGTH_SHORT).show();
        });

        // ====================== START OF FIX ======================
        // Semak jika aktiviti ini dibuka dari halaman Bookmark
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("PRODUCT_NAME_FROM_BOOKMARK")) {
            // Jika ya, dapatkan nama produk
            String productNameFromBookmark = intent.getStringExtra("PRODUCT_NAME_FROM_BOOKMARK");

            // Letakkan nama produk dalam kotak carian (pilihan, tetapi bagus untuk UI)
            searchEditText.setText(productNameFromBookmark);

            // Terus jalankan fungsi carian
            searchForProduct(productNameFromBookmark);
        }
        // ======================= END OF FIX =======================
    }

    private void searchForProduct(String productNameQuery) {
        // Logik carian anda akan kekal di sini
        // Pastikan nama produk dalam data dummy di BookmarkActivity sama dengan logik carian di sini
        // Contoh: "Maggie" dan bukan "Maggi Kari"
        if (productNameQuery.equalsIgnoreCase("maggie")) {
            productImage.setImageResource(R.drawable.maggie);
            productName.setText("MAGGI");
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
                    "Category: Food & Instant Noodles\n" +
                    "Country of Origin: UK\n";
            String statusText = "Status : <font color='#FF0000'>BOYCOTT!</font>";
            String fullDetailsText = details + statusText;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                productDetails.setText(Html.fromHtml(fullDetailsText, Html.FROM_HTML_MODE_LEGACY));
            } else {
                productDetails.setText(Html.fromHtml(fullDetailsText));
            }
            productDetailsContainer.setVisibility(View.VISIBLE);

        } else {
            Toast.makeText(this, "Product not found!", Toast.LENGTH_SHORT).show();
            productDetailsContainer.setVisibility(View.GONE);
        }
    }
}
