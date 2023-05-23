package com.tadosalvo.se430project;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorkoutActivity extends AppCompatActivity {

    private Button homeButton;
    private Button dietButton;
    private Button encyclopediaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_program);

        homeButton = (Button) findViewById(R.id.homeButton2);
        dietButton = (Button) findViewById(R.id.dietPlanButton2);
        encyclopediaButton = (Button) findViewById(R.id.encyclopediaButton2);


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
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

    }

    public void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
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
}
