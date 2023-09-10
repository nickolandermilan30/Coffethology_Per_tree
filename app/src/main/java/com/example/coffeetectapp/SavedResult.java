package com.example.coffeetectapp;

import android.graphics.Bitmap;

public class SavedResult {
    private String diseaseName;
    private String severityLevel; // Keep severity level as string
    private Bitmap imageBitmap;

    public SavedResult(String diseaseName, String severityLevel, Bitmap imageBitmap) {
        this.diseaseName = diseaseName;
        this.severityLevel = severityLevel;
        this.imageBitmap = imageBitmap;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }
}