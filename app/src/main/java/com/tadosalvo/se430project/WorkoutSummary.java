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

import org.w3c.dom.Text;

import java.util.Map;

public class WorkoutSummary extends AppCompatActivity {

    private Button homeButton;
    private Button encyclopediaButton;
    private Button workoutButton;
    private Button logoutButton;
    private Button dietButton;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private TextView squatReps, squatSets, lungeReps, lungeSets, deadliftReps, deadliftSets;
    private TextView overHeadPressReps, overHeadPressSets, benchPressReps, benchPressSets, pushUpReps, pushUpSets;
    private TextView curlUpReps, curlUpSets, sitUpReps, sitUpSets, sideBridgeReps, sideBridgeSets;
    private TextView runReps, runSets, rowsReps, rowsSets, cyclingReps, cyclingSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_summary);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        homeButton = (Button) findViewById(R.id.homeButton5);
        workoutButton = (Button) findViewById(R.id.workoutProgramButton5);
        encyclopediaButton = (Button) findViewById(R.id.encyclopediaButton5);
        dietButton = (Button) findViewById(R.id.dietPlanButton5);
        logoutButton = (Button) findViewById(R.id.logoutButton5);

        squatReps = findViewById(R.id.lbl_LowerBody_Squats_Reps);
        squatSets = findViewById(R.id.lbl_LowerBody_Squats_Sets);
        lungeReps = findViewById(R.id.lbl_LowerBody_Lunge_Reps);
        lungeSets = findViewById(R.id.lbl_LowerBody_Lunge_Sets);
        deadliftReps = findViewById(R.id.lbl_LowerBody_Deadlift_Reps);
        deadliftSets = findViewById(R.id.lbl_LowerBody_Deadlift_Sets);

        overHeadPressReps = findViewById(R.id.lbl_UpperBody_OverHeadPress_Reps);
        overHeadPressSets = findViewById(R.id.lbl_UpperBody_OverHeadPress_Sets);
        benchPressReps = findViewById(R.id.lbl_UpperBody_BenchPress_Reps);
        benchPressSets = findViewById(R.id.lbl_UpperBody_BenchPress_Sets);
        pushUpReps = findViewById(R.id.lbl_UpperBody_PushUp_Reps);
        pushUpSets = findViewById(R.id.lbl_UpperBody_PushUp_Sets);

        curlUpReps = findViewById(R.id.lbl_Core_CurlUp_Reps);
        curlUpSets = findViewById(R.id.lbl_Core_CurlUp_Sets);
        sitUpReps = findViewById(R.id.lbl_Core_SitUp_Reps);
        sitUpSets = findViewById(R.id.lbl_Core_SitUp_Sets);
        sideBridgeReps = findViewById(R.id.lbl_Core_SideBridge_Reps);
        sideBridgeSets = findViewById(R.id.lbl_Core_SideBridge_Sets);

        runReps = findViewById(R.id.lbl_Cardio_Run_Reps);
        runSets = findViewById(R.id.lbl_Cardio_Run_Sets);
        rowsReps = findViewById(R.id.lbl_Cardio_Rows_Reps);
        rowsSets = findViewById(R.id.lbl_Cardio_Rows_Sets);
        cyclingReps = findViewById(R.id.lbl_Cardio_Cycling_Reps);
        cyclingSets = findViewById(R.id.lbl_Cardio_Cycling_Sets);

        loadWorkoutPlanData();

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
        dietButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { openDietActivity(); }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                openMainActivity();
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
    public void openDietActivity() {
        Intent intent = new Intent(this, DietActivity.class);
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

    public void loadWorkoutPlanData() {
        DocumentReference docRef = db.collection("Users").document(mAuth.getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()) {
                        Map<String, Object> data = document.getData();
                        // Lower Body Data
                        if(document.contains("squatsReps")) {
                            squatReps.setText(data.get("squatsReps").toString());
                        }
                        if(document.contains("squatsSets")) {
                            squatSets.setText(data.get("squatsSets").toString());
                        }
                        if(document.contains("lungeReps")) {
                            lungeReps.setText(data.get("lungeReps").toString());
                        }
                        if(document.contains("lungeSets")) {
                            lungeSets.setText(data.get("lungeSets").toString());
                        }
                        if(document.contains("deadliftReps")) {
                            deadliftReps.setText(data.get("deadliftReps").toString());
                        }
                        if(document.contains("deadliftSets")) {
                            deadliftSets.setText(data.get("deadliftSets").toString());
                        }

                        // Upper Body Data
                        if(document.contains("overheadPressReps")) {
                            overHeadPressReps.setText(data.get("overheadPressReps").toString());
                        }
                        if(document.contains("overheadPressSets")) {
                            overHeadPressSets.setText(data.get("overheadPressSets").toString());
                        }
                        if(document.contains("benchPressReps")) {
                            benchPressReps.setText(data.get("benchPressReps").toString());
                        }
                        if(document.contains("benchPressSets")) {
                            benchPressSets.setText(data.get("benchPressSets").toString());
                        }
                        if(document.contains("pushUpReps")) {
                            pushUpReps.setText(data.get("pushUpReps").toString());
                        }
                        if(document.contains("pushUpSets")) {
                            pushUpSets.setText(data.get("pushUpSets").toString());
                        }

                        // Core Data
                        if(document.contains("curlUpReps")) {
                            curlUpReps.setText(data.get("curlUpReps").toString());
                        }
                        if(document.contains("curlUpSets")) {
                            curlUpSets.setText(data.get("curlUpSets").toString());
                        }
                        if(document.contains("sitUpReps")){
                            sitUpReps.setText(data.get("sitUpReps").toString());
                        }
                        if(document.contains("sitUpSets")) {
                            sitUpSets.setText(data.get("sitUpSets").toString());
                        }
                        if(document.contains("sideBridgeReps")) {
                            sideBridgeReps.setText(data.get("sideBridgeReps").toString());
                        }
                        if(document.contains("sideBridgeSets")) {
                            sideBridgeSets.setText(data.get("sideBridgeSets").toString());
                        }

                        // Cardio Data
                        if(document.contains("runReps")) {
                            runReps.setText(data.get("runReps").toString());
                        }
                        if(document.contains("runSets")) {
                            runSets.setText(data.get("runSets").toString());
                        }
                        if(document.contains("rowsReps")){
                            rowsReps.setText(data.get("rowsReps").toString());
                        }
                        if(document.contains("rowsSets")) {
                            rowsSets.setText(data.get("rowsSets").toString());
                        }
                        if(document.contains("cyclingReps")) {
                            cyclingReps.setText(data.get("cyclingReps").toString());
                        }
                        if(document.contains("cyclingSets")) {
                            cyclingSets.setText(data.get("cyclingSets").toString());
                        }

                    } else {
                        Toast.makeText(getApplicationContext(),"Reps or Sets not set", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Data retrieval unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}