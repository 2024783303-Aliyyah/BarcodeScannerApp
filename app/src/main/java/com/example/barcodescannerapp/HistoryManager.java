package com.example.barcodescannerapp;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HistoryManager {
    private static final String PREF_NAME = "ProductPrefs";
    private static final String KEY_HISTORY = "search_history";
    private final SharedPreferences sharedPreferences;

    public HistoryManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void addToHistory(String productName) {
        Set<String> historySet = sharedPreferences.getStringSet(KEY_HISTORY, new LinkedHashSet<>());
        // Create a new set to avoid immutability issues
        Set<String> updatedSet = new LinkedHashSet<>(historySet);
        updatedSet.add(productName);
        sharedPreferences.edit().putStringSet(KEY_HISTORY, updatedSet).apply();
    }

    public List<String> getHistoryList() {
        Set<String> historySet = sharedPreferences.getStringSet(KEY_HISTORY, new LinkedHashSet<>());
        return new ArrayList<>(historySet);
    }
}