// Fail: BookmarkManager.java
package com.example.barcodescannerapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookmarkManager {

    private static final String PREFS_NAME = "BookmarkPrefs";
    private static final String BOOKMARKS_KEY = "bookmarked_products";
    private final SharedPreferences sharedPreferences;

    // Constructor untuk inisialisasi SharedPreferences
    public BookmarkManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Fungsi untuk MENAMBAH produk ke senarai bookmark
    public void addBookmark(String productName) {
        // 1. Dapatkan senarai sedia ada
        Set<String> bookmarks = getBookmarks();
        // 2. Tambah produk baru
        bookmarks.add(productName);
        // 3. Simpan senarai yang telah dikemaskini
        saveBookmarks(bookmarks);
    }

    // Fungsi untuk MENDAPATKAN semua produk yang telah di-bookmark
    public Set<String> getBookmarks() {
        // Mengembalikan salinan HashSet yang boleh diubah suai
        return new HashSet<>(sharedPreferences.getStringSet(BOOKMARKS_KEY, new HashSet<>()));
    }

    // Fungsi untuk MENYIMPAN senarai ke SharedPreferences
    private void saveBookmarks(Set<String> bookmarks) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(BOOKMARKS_KEY, bookmarks);
        editor.apply(); // Simpan secara asynchronous
    }
}
