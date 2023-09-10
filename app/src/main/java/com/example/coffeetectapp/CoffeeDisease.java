package com.example.coffeetectapp;

public class CoffeeDisease {
    private String diseaseName;
    private String[] severityLevels;

    public CoffeeDisease(String diseaseName, String[] severityLevels) {
        this.diseaseName = diseaseName;
        this.severityLevels = severityLevels;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String[] getSeverityLevels() {
        return severityLevels;
    }
}
