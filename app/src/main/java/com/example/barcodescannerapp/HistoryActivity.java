package com.example.barcodescannerapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collections;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView historyRecyclerView;
    private HistoryAdapter historyAdapter;
    private HistoryManager historyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyManager = new HistoryManager(this);

        // Use the new RecyclerView ID from your XML
        historyRecyclerView = findViewById(R.id.history_recycler_view);

        List<String> historyItems = historyManager.getHistoryList(); // Assuming getHistoryList() is your method
        Collections.reverse(historyItems); // Show most recent first

        // Setup the RecyclerView
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new HistoryAdapter(this, historyItems); // Use the new adapter
        historyRecyclerView.setAdapter(historyAdapter);
    }
}

