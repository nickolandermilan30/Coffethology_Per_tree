package com.example.coffeetectapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Result_Activity extends AppCompatActivity {

    Button backr, saveButton;
    private boolean isSaving = false;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        backr = findViewById(R.id.capagain);
        saveButton = findViewById(R.id.saveButton);

        backr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result_Activity.this, Camera.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSaving) {
                    isSaving = true;

                    // Retrieve data from intent
                    String diseaseName = getIntent().getStringExtra("diseaseName");
                    String severityLevel = getIntent().getStringExtra("severityLevel");

                    // Retrieve the byte array from the intent
                    byte[] byteArray = getIntent().getByteArrayExtra("image");

                    // Convert the byte array back to a Bitmap
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 1;
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                    Bitmap imageBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);

                    // Resize the Bitmap
                    int targetWidth = 800;
                    int targetHeight = 600;
                    imageBitmap = Bitmap.createScaledBitmap(imageBitmap, targetWidth, targetHeight, false);

                    // Find the TextViews and ImageView in the layout
                    TextView diseaseTextView = findViewById(R.id.diseaseNameTextView);
                    TextView severityTextView = findViewById(R.id.severityLevelTextView);
                    ImageView resultImageView = findViewById(R.id.resultImageView);

                    // Set the text for disease and severity
                    diseaseTextView.setText(diseaseName);
                    severityTextView.setText(severityLevel);

                    // Set the image in the ImageView
                    resultImageView.setImageBitmap(imageBitmap);

                    // Create a SavedResult object
                    SavedResult savedResult = new SavedResult(diseaseName, severityLevel, imageBitmap);






                    // Redirect to another activity (Folders)
                    Intent intent;
                    if (SavedResultsManager.getSavedResultsCount() < 10) {
                        intent = new Intent(Result_Activity.this, Folders.class);
                    } else if (SavedResultsManager.getSavedResultsCountHistory2() < 10) {

                        intent = new Intent(Result_Activity.this, Folders.class);
                    } else if (SavedResultsManager.getSavedResultsCountHistory3() < 10) {

                        intent = new Intent(Result_Activity.this, Folders.class);
                    } else if (SavedResultsManager.getSavedResultsCountHistory4() < 10) {

                        intent = new Intent(Result_Activity.this, Folders.class);
                    } else if (SavedResultsManager.getSavedResultsCountHistory5() < 10) {

                        intent = new Intent(Result_Activity.this, Folders.class);
                    } else {
                        // Limit the number of results in history6 to 10
                        if (SavedResultsManager.getSavedResultsCountHistory6() < 10) {
                            intent = new Intent(Result_Activity.this, Folders.class);

                        } else {
                            // Show an alert dialog if history6 is full
                            AlertDialog.Builder builder = new AlertDialog.Builder(Result_Activity.this);
                            builder.setTitle("The Folder is Full");
                            builder.setMessage("You have reached the maximum limit of saved results");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.create().show();

                            intent = new Intent(Result_Activity.this, Folders.class);
                        }
                    }
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

// Check if `History` is full
                    if (SavedResultsManager.getSavedResultsCount() < 10) {
                        // If not full, add to `History`
                        SavedResultsManager.addSavedResult(savedResult);
                    } else if (SavedResultsManager.getSavedResultsCountHistory2() < 10) {

                        // If `History` is full but `History2` is not, add to `History2`
                        SavedResultsManager.addSavedResultToHistory2(savedResult);
                    } else if (SavedResultsManager.getSavedResultsCountHistory3() < 10) {

                        // If `History2` is full but `History3` is not, add to `History3`
                        SavedResultsManager.addSavedResultToHistory3(savedResult);
                    } else if (SavedResultsManager.getSavedResultsCountHistory4() < 10) {

                        // If `History3` is full but `History4` is not, add to `History4`
                        SavedResultsManager.addSavedResultToHistory4(savedResult);
                    } else if (SavedResultsManager.getSavedResultsCountHistory5() < 10) {

                        // If `History4` is full but `History5` is not, add to `History5`
                        SavedResultsManager.addSavedResultToHistory5(savedResult);
                    } else if (SavedResultsManager.getSavedResultsCountHistory6() < 10) {

                        // If `History5` is full but `History6` is not, add to `History6`
                        SavedResultsManager.addSavedResultToHistory6(savedResult);
                    }





                    isSaving = false;
                }
            }
        });


        // Get the data from the intent
        String diseaseName = getIntent().getStringExtra("diseaseName");
        String severityLevel = getIntent().getStringExtra("severityLevel");

        // Retrieve the byte array from the intent
        byte[] byteArray = getIntent().getByteArrayExtra("image");

        // Convert the byte array back to a Bitmap
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        // Find the TextViews and ImageView in the layout
        TextView diseaseTextView = findViewById(R.id.diseaseNameTextView);
        TextView severityTextView = findViewById(R.id.severityLevelTextView);
        ImageView resultImageView = findViewById(R.id.resultImageView);

        // Set the text for disease and severity
        diseaseTextView.setText(diseaseName);
        severityTextView.setText(severityLevel);

        // Set the image in the ImageView
        resultImageView.setImageBitmap(imageBitmap);


        // Add back button functionality
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

    private void saveResultToStorage(String diseaseName, String severityLevel, Bitmap imageBitmap) {

    }
}