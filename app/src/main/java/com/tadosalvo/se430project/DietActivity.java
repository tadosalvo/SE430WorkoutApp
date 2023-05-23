package com.tadosalvo.se430project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DietActivity extends AppCompatActivity {


    private Button homeButton;
    private Button encyclopediaButton;
    private Button workoutButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        homeButton = (Button) findViewById(R.id.homeButton3);
        workoutButton = (Button) findViewById(R.id.workoutProgramButton3);
        encyclopediaButton = (Button) findViewById(R.id.encyclopediaButton3);
        logoutButton = (Button) findViewById(R.id.logoutButton2);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });

        encyclopediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEncyclopediaActivity();
            }
        });

        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkoutActivity();
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutButtonAction();
            }
        });


    }
    public void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void openWorkoutActivity() {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void openEncyclopediaActivity() {
        Intent intent = new Intent(this, EncylopediaActivity.class);
        startActivity(intent);
    }

    public void logoutButtonAction() {
        // log out button action goes here
    }
}