package com.example.barcodescannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ResourcesActivity extends AppCompatActivity {

    private RecyclerView resourcesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resources);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Back Button Function
        ImageView backButton = findViewById(R.id.btnBack);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // RecyclerView Setup
        resourcesRecyclerView = findViewById(R.id.resources_recycler_view);
        List<ResourceManager> resourceList = new ArrayList<>();
        resourceList.add(new ResourceManager("Maggi", "An introduction to the Boycott, Divestment, and Sanctions movement and its goals."));
        resourceList.add(new ResourceManager("Nestle Product Boycott??", "List of the Nestle Product that are associated with zionist"));
        resourceList.add(new ResourceManager("Why Boycott Certain Brands?", "A deep dive into the specific reasons some companies are targeted for boycotts."));
        resourceList.add(new ResourceManager("Alternative Products", "Discover local and ethical alternatives to boycotted goods."));
        resourceList.add(new ResourceManager("The Impact of Grassroots Movements", "See how collective action can lead to corporate and political change."));

        resourcesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ResourceAdapter adapter = new ResourceAdapter(resourceList);
        resourcesRecyclerView.setAdapter(adapter);

        // Navigation Setup
        setupNavigation();
    }

    private void setupNavigation() {
        BottomNavigationView nav = findViewById(R.id.bottom);
        if (nav != null) {
            nav.setSelectedItemId(R.id.nav_resources);

            nav.setOnItemSelectedListener(item -> {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    startActivity(new Intent(this, HomepageActivity.class));
                    return true;
                } else if (id == R.id.nav_history) {
                    startActivity(new Intent(this, HistoryActivity.class));
                    return true;
                } else if (id == R.id.nav_bookmarks) {
                    startActivity(new Intent(this, BookmarkActivity.class));
                    return true;
                } else if (id == R.id.nav_resources) {
                    return true;
                }
                return false;
            });
        }
    }
}
