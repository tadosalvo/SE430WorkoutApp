package com.tadosalvo.se430project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class DietActivity extends AppCompatActivity {
    private Button homeButton;
    private Button encyclopediaButton;
    private Button workoutButton;
    private Button logoutButton;
    private Button breakfastButton, lunchButton, dinnerButton;
    private TextView breakfastItems,breakfastCal;
    private TextView lunchItems,lunchCal;
    private TextView dinnerItems,dinnerCal;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        homeButton = (Button) findViewById(R.id.homeButton3);
        workoutButton = (Button) findViewById(R.id.workoutProgramButton3);
        encyclopediaButton = (Button) findViewById(R.id.encyclopediaButton3);
        logoutButton = (Button) findViewById(R.id.logoutButton2);
        breakfastButton = findViewById(R.id.BreakfastButton);
        lunchButton = findViewById(R.id.LunchButton);
        dinnerButton = findViewById(R.id.DinnerButton);
        breakfastItems = findViewById(R.id.lbl_UpperBody_Items);
        breakfastCal = findViewById(R.id.lbl_UpperBody_Cal);
        lunchItems = findViewById(R.id.lbl_Lunch_Items);
        lunchCal = findViewById(R.id.lbl_Lunch_Cal);
        dinnerItems = findViewById(R.id.lbl_Dinner_Items);
        dinnerCal = findViewById(R.id.lbl_Dinner_Cal);

        loadDietPlanData();

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
                mAuth.signOut();
                openMainActivity();
            }
        });
        breakfastButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openBreakfastMenuActivity();
            }
        });

        lunchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openLunchMenuActivity();
            }
        });

        dinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDinnerMenuActivity();
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

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openBreakfastMenuActivity(){
        Intent intent = new Intent(this, BreakfastMenuActivity.class);
        startActivity(intent);
    }

    public void openLunchMenuActivity(){
        Intent intent = new Intent(this, LunchMenuActivity.class);
        startActivity(intent);
    }

    public void openDinnerMenuActivity(){
        Intent intent = new Intent(this, DinnerMenuActivity.class);
        startActivity(intent);
    }

    public void loadDietPlanData(){
        DocumentReference docRef = db.collection("Users").document(mAuth.getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Map<String, Object> data = document.getData();
                        if(document.contains("breakfast")) {
                            breakfastItems.setText(data.get("breakfast").toString());
                            breakfastCal.setText(data.get("breakfast_cal").toString() + " calories");
                        }
                        if(document.contains("lunch")) {
                            lunchItems.setText(data.get("lunch").toString());
                            lunchCal.setText(data.get("lunch_cal").toString() + " calories");
                        }
                        if(document.contains("dinner")) {
                            dinnerItems.setText(data.get("dinner").toString());
                            dinnerCal.setText(data.get("dinner_cal").toString() + " calories");
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Diet Plan Not Selected", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Data retrieval unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}