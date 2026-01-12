package com.example.barcodescannerapp;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);

        ListView listView = findViewById(R.id.historyListView);
        HistoryManager historyManager = new HistoryManager(this);
        List<String> history = historyManager.getHistoryList();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, history);
        listView.setAdapter(adapter);

        // Optional: Click a history item to search it again
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Logic to return to search page and search this item
        });
    }
}