package com.example.barcodescannerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ResourcesActivity extends AppCompatActivity {

    private RecyclerView resourcesRecyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resources);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Find the RecyclerView in your layout
        resourcesRecyclerView = findViewById(R.id.resources_recycler_view);

        // 2. Create your dummy data
        List<ResourceManager> resourceList = new ArrayList<>();
        resourceList.add(new ResourceManager("Maggi", "An introduction to the Boycott, Divestment, and Sanctions movement and its goals."));
        resourceList.add(new ResourceManager("Nestle Product Boycott??", "List of the Nestle Product that are associated with zionist"));
        resourceList.add(new ResourceManager("Why Boycott Certain Brands?", "A deep dive into the specific reasons some companies are targeted for boycotts."));
        resourceList.add(new ResourceManager("Alternative Products", "Discover local and ethical alternatives to boycotted goods."));
        resourceList.add(new ResourceManager("The Impact of Grassroots Movements", "See how collective action can lead to corporate and political change."));

        // 3. Set up the RecyclerView
        resourcesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ResourceAdapter adapter = new ResourceAdapter(resourceList);
        resourcesRecyclerView.setAdapter(adapter);
    }
}
