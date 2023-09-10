package com.example.coffeetectapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Starting extends AppCompatActivity {

    private CheckBox agreeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        // Hide the action bar (app bar or title bar)
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }



        // Find the "Next" ImageButton by its ID
        ImageButton btnNext = findViewById(R.id.btnNextActivity);

        // Set an OnClickListener for the ImageButton
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the dialog with Agree and Disagree options
                showAgreeDisagreeDialog();
            }
        });
    }

    // Method to show the Agree/Disagree dialog
    private void showAgreeDisagreeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Non-Disclosure Agreement");
        builder.setMessage("The Application is not Exactly accurate of Coffee leaf But the" +
                " application needs high resolution capture image to identify coffee leaf accurately.");

        // Set the layout for the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog_layout, null);
        builder.setView(dialogView);

        // Find the checkboxes in the custom dialog layout
        CheckBox checkAgree = dialogView.findViewById(R.id.checkAgree);
        CheckBox checkAgree2 = dialogView.findViewById(R.id.checkAgree2);

        // Add Agree button
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Check if the Agree checkbox is checked
                if (checkAgree.isChecked()) {
                    // Start the next activity
                    Intent intent = new Intent(Starting.this, Home.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                }

            }

        });
        // Add Agree button
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Check if the Agree checkbox is checked
                if (checkAgree2.isChecked()) {
                    // Start the next activity
                    Intent intent = new Intent(Starting.this, Home.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                }

            }

        });


        // Add Disagree button
        builder.setNegativeButton("Disagree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Show a second dialog for additional options
                showDisagreeOptionsDialog();
            }
        });

        // Show the initial dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Disable the Agree button initially
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);

        // Set a listener to enable the Agree button when the checkbox is checked
        checkAgree.setOnCheckedChangeListener((buttonView, isChecked) -> {
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(isChecked);
        });
    }

    // Method to show the second Disagree options dialog
    private void showDisagreeOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Disagreement Options");
        builder.setMessage("Are you really sure to disagree the agreement");

        // Add additional options buttons
        builder.setPositiveButton("Back to Agreement", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Show the Non-Disclosure Agreement dialog again
                showAgreeDisagreeDialog();
            }
        });

        builder.setNegativeButton("Exit App", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Exit the app or perform desired action
                // For example:
                finishAffinity();
            }
        });

        // Show the second dialog
        builder.create().show();
    }
}