package com.example.coffeetectapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class History5 extends AppCompatActivity {

    private List<SavedResult> savedResultsList = new ArrayList<>();
    private boolean isRecommendationClickable = true;
    private Button recommendationButton;
    Button cal, his;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history5);  // I-update ang layout na tukoy para sa History3

        // Hide the action bar (app bar or title bar)
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        cal = findViewById(R.id.showGraphButton);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History5.this, Calendar.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });

        recommendationButton = findViewById(R.id.recommendationButton);
        Button clearResultsButton = findViewById(R.id.clearResultsButton);

        // Retrieve saved results from SavedResultsManager for History3
        List<SavedResult> savedResults = SavedResultsManager.getHistory5List();  // Ito ay maaaring pagbabago depende sa iyong implementation

        // Initialize RecyclerView and set adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        SavedResultsAdapter adapter = new SavedResultsAdapter(savedResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Get recommendation for History3
        String mostFrequentAndSevereDisease = SavedResultsManager.getMostFrequentAndSevereDisease();
        int severityLevel = SavedResultsManager.getSeverityForDisease(mostFrequentAndSevereDisease);

        // Configure recommendation button
        Button recommendationButton = findViewById(R.id.recommendationButton);

        // Check if there are at least 10 saved results to show the recommendation button
        if (SavedResultsManager.getHistory5List().size() < 10) {  // Ito ay maaaring pagbabago depende sa iyong implementation
            recommendationButton.setVisibility(View.GONE); // Hide the recommendation button
        }

        recommendationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRecommendationClickable) { // Check if button is clickable
                    Intent intent = new Intent(History5.this, RecommendationActivity.class);
                    intent.putExtra("diseaseName", mostFrequentAndSevereDisease);
                    intent.putExtra("severityLevel", severityLevel);
                    startActivity(intent);
                }
            }
        });

        clearResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(History5.this);
                builder.setTitle("Clear Saved Results");
                builder.setMessage("Are you sure you want to clear all saved results? This action cannot be undone.");
                builder.setPositiveButton("Clear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        isRecommendationClickable = false;
                        SavedResultsManager.clearHistory5Results();  // Ito ay maaaring pagbabago depende sa iyong implementation

                        // Refresh the RecyclerView
                        savedResultsList.clear();
                        RecyclerView recyclerView = findViewById(R.id.recyclerView);
                        SavedResultsAdapter adapter = new SavedResultsAdapter(savedResultsList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(History5.this));
                        recyclerView.setAdapter(adapter);

                        recommendationButton.setVisibility(View.GONE);
                        isRecommendationClickable = true;
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing, user cancelled the clear operation
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
