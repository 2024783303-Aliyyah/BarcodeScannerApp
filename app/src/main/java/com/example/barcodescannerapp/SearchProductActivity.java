package com.example.barcodescannerapp;
// Di dalam SearchProductActivity.java
// Di dalam SearchProductActivity.java

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

    // Deklarasi semua komponen UI
    private EditText searchEditText;
    private Button searchButton;
    private ConstraintLayout productDetailsContainer;

    // Deklarasi komponen di dalam kad produk
    private ImageView productImage;
    private TextView productName;
    private TextView productDetails;
    private Button continueButton;
    private ImageView bookmarkButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        // Sambungkan semua komponen dari XML
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        productDetailsContainer = findViewById(R.id.product_details_container);

        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        productDetails = findViewById(R.id.product_details);
        continueButton = findViewById(R.id.continue_button);
        bookmarkButton = findViewById(R.id.bookmark_button);

        // Fungsi untuk butang carian
        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString().trim();
            searchForProduct(query);
        });

        // Fungsi untuk butang "Continue"
        continueButton.setOnClickListener(v -> {
            productDetailsContainer.setVisibility(View.GONE);
            searchEditText.setText(""); // Kosongkan kotak carian
        });

        // Fungsi untuk butang bookmark
        bookmarkButton.setOnClickListener(v -> {
            // Logik untuk menyimpan ke bookmark
            Toast.makeText(this, "Product saved to Bookmarked!", Toast.LENGTH_SHORT).show();
        });
    }

    // Fungsi utama untuk mencari dan memaparkan produk
    private void searchForProduct(String productNameQuery) {
        if (productNameQuery.equalsIgnoreCase("maggie")) {
            // Tetapkan gambar dan nama produk (kekal sama)
            productImage.setImageResource(R.drawable.maggie);
            productName.setText("MAGGI");

            // ====================== START OF FIX ======================
            // 1. Bina teks butiran tanpa status
            String details = "Product Name: Maggi\n" +
                    "Company: Nestlé\n" +
                    "Category: Food & Instant Noodles\n" +
                    "Country of Origin: Switzerland (Nestlé HQ)\n";

            // 2. Bina teks status dengan tag warna HTML
            String statusText = "Status : <font color='#FF0000'>BOYCOTT!</font>"; // #FF0000 adalah kod warna merah

            // 3. Gabungkan teks butiran dan teks status
            String fullDetailsText = details + statusText;

            // 4. Guna Html.fromHtml untuk memaparkan teks yang telah diformat
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                productDetails.setText(Html.fromHtml(fullDetailsText, Html.FROM_HTML_MODE_LEGACY));
            } else {
                productDetails.setText(Html.fromHtml(fullDetailsText));
            }
            // ======================= END OF FIX =======================

            // Paparkan kad (kekal sama)
            productDetailsContainer.setVisibility(View.VISIBLE);

        } else if (productNameQuery.equalsIgnoreCase("kitkat")) {

            productImage.setImageResource(R.drawable.kitkat);
            productName.setText("KITKAT");

            String details = "Product Name: Kitkat\n" +
                    "Company: Nestlé\n" +
                    "Category: Food & Instant Noodles\n" +
                    "Country of Origin: UK\n";

            // 2. Bina teks status dengan tag warna HTML
            String statusText = "Status : <font color='#FF0000'>BOYCOTT!</font>"; // #FF0000 adalah kod warna merah

            // 3. Gabungkan teks butiran dan teks status
            String fullDetailsText = details + statusText;

            // 4. Guna Html.fromHtml untuk memaparkan teks yang telah diformat
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                productDetails.setText(Html.fromHtml(fullDetailsText, Html.FROM_HTML_MODE_LEGACY));
            } else {
                productDetails.setText(Html.fromHtml(fullDetailsText));
            }
            // ======================= END OF FIX =======================

            // Paparkan kad (kekal sama)
            productDetailsContainer.setVisibility(View.VISIBLE);

        }
        else if (productNameQuery.equalsIgnoreCase("vico")) {

            productImage.setImageResource(R.drawable.vico);
            productName.setText("VICO");

            String details = "Product Name: Vico\n" +
                    "Company: Maestro Swiss, VICO Mfg\n" +
                    "Category: Food & Instant Noodles\n" +
                    "Country of Origin: Malaysia\n";

            // 2. Bina teks status dengan tag warna HTML
            String statusText = "Status : <font color='#8fce00'>SAFE!</font>"; // #FF0000 adalah kod warna merah

            // 3. Gabungkan teks butiran dan teks status
            String fullDetailsText = details + statusText;

            // 4. Guna Html.fromHtml untuk memaparkan teks yang telah diformat
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                productDetails.setText(Html.fromHtml(fullDetailsText, Html.FROM_HTML_MODE_LEGACY));
            } else {
                productDetails.setText(Html.fromHtml(fullDetailsText));
            }
            // ======================= END OF FIX =======================

            // Paparkan kad (kekal sama)
            productDetailsContainer.setVisibility(View.VISIBLE);

        }else {
            // Jika produk tidak ditemui
            Toast.makeText(this, "Product not found!", Toast.LENGTH_SHORT).show();
            productDetailsContainer.setVisibility(View.GONE);
        }
    }
    public void goBack(View view)
    {
        finish();
    }
}
