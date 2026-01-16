package com.example.barcodescannerapp;

public class ResourceManager {
    private final String title;
    private final String description;

    public ResourceManager(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}