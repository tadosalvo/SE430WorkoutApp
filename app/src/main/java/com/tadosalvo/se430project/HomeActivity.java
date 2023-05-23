package com.tadosalvo.se430project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {


    private Button workoutButton;
    private Button dietButton;
    private Button encyclopediaButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        workoutButton = (Button) findViewById(R.id.workoutProgramButton);
        dietButton = (Button) findViewById(R.id.dietPlanButton);
        encyclopediaButton = (Button) findViewById(R.id.encyclopediaButton);
        logoutButton = (Button) findViewById(R.id.LogoutButton);

        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkoutActivity();
            }
        });

        dietButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDietActivity();
            }
        });

        encyclopediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEncyclopediaActivity();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutButtonAction();
            }
        });


    }


    public void openWorkoutActivity() {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }
    public void openDietActivity() {
        Intent intent = new Intent(this, DietActivity.class);
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