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

import org.w3c.dom.Document;
import org.w3c.dom.Text;

import java.util.Map;

public class WorkoutActivity extends AppCompatActivity {

    private Button homeButton;
    private Button dietButton;
    private Button encyclopediaButton;
    private Button logoutButton;
    private Button lowerBodyButton, upperBodyButton, coreButton, cardioButton;
    private TextView upperBodyItems, upperBodyCal;
    private TextView lowerBodyItems, lowerBodyCal;
    private TextView coreItems, coreCal;
    private TextView cardioItems, cardioCal;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_program);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        homeButton = (Button) findViewById(R.id.homeButton2);
        dietButton = (Button) findViewById(R.id.dietPlanButton2);
        encyclopediaButton = (Button) findViewById(R.id.encyclopediaButton2);
        logoutButton = (Button) findViewById(R.id.logoutButton);

        lowerBodyButton = findViewById(R.id.lowerBodyButton);
        upperBodyButton = findViewById(R.id.upperBodyButton);
        coreButton = findViewById(R.id.coreStrengthButton);
        cardioButton = findViewById(R.id.cardioButton);

        lowerBodyItems = findViewById(R.id.lbl_LowerBody_Items);
        upperBodyItems = findViewById(R.id.lbl_UpperBody_Items);
        coreItems = findViewById(R.id.lbl_Core_Items);
        cardioItems = findViewById(R.id.lbl_Cardio_Items);

        lowerBodyCal = findViewById(R.id.lbl_LowerBody_Cal);
        upperBodyCal = findViewById(R.id.lbl_UpperBody_Cal);
        coreCal = findViewById(R.id.lbl_Core_Cal);
        cardioCal = findViewById(R.id.lbl_Cardio_Cal);

        loadWorkoutPlanData();


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

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                openMainActivity();
            }
        });

        lowerBodyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLowerBodyMenuActivity();
            }
        });
        upperBodyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openUpperBodyMenuActivity();
            }
        });
        coreButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCoreMenuActivity();
            }
        });
        cardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCardioMenuActivity();
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

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openLowerBodyMenuActivity() {
        Intent intent = new Intent(this, LowerBodyMenuActivity.class);
        startActivity(intent);
    }

    public void openUpperBodyMenuActivity() {
        Intent intent = new Intent(this, UpperBodyMenuActivity.class);
        startActivity(intent);
    }

    public void openCoreMenuActivity() {
        Intent intent = new Intent(this, CoreMenuActivity.class);
        startActivity(intent);
    }

    public void openCardioMenuActivity() {
        Intent intent = new Intent(this, CardioMenuActivity.class);
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
                        if(document.contains("upperBody")) {
                            upperBodyItems.setText(data.get("upperBody").toString());
                            upperBodyCal.setText(data.get("upperBody_cal").toString());
                        }
                        if(document.contains("lowerBody")) {
                            lowerBodyItems.setText(data.get("lowerBody").toString());
                            lowerBodyCal.setText(data.get("lowerBody_cal").toString());
                        }
                        if(document.contains("core")) {
                            coreItems.setText(data.get("core").toString());
                            coreCal.setText(data.get("core_cal").toString());
                        }
                        if(document.contains("cardio")) {
                            cardioItems.setText(data.get("cardio").toString());
                            cardioCal.setText(data.get("cardio_cal").toString());
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Workout Plan Not Selected", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Data retrieval unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
