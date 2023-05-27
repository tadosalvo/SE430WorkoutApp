package com.tadosalvo.se430project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpperBodyMenuActivity extends AppCompatActivity {


    private Button doneButton;
    private CheckBox overHeadPress, benchPress, pushUp;
    private WorkoutPlan upperBody;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private CollectionReference users;
    private ArrayList<String> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper_body_menu);

        upperBody = new WorkoutPlan("Upper Body");
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        users = db.collection("Users");

        doneButton = findViewById(R.id.btn_upper_done);
        overHeadPress = findViewById(R.id.chkOverheadPress);
        benchPress = findViewById(R.id.chkBenchPress);
        pushUp = findViewById(R.id.chkPushup);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItems = upperBody.getItems();
                Map<String, Object> data = new HashMap<>();
                data.put("username", mAuth.getCurrentUser().getEmail());
                data.put("upperBody", selectedItems);
                data.put("upperBody_cal", upperBody.getTotalCal());
                users.document(mAuth.getCurrentUser().getUid()).set(data, SetOptions.merge());
                openWorkoutActivity();
            }
        });

    }

    public void openWorkoutActivity() {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void onUpperBodyChkBoxStatChange(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.chkOverheadPress:
                if (checked) {
                    upperBody.add("Overhead Press", "361");
                } else {
                    upperBody.remove("Overhead Press");
                }
                break;
            case R.id.chkBenchPress:
                if (checked) {
                    upperBody.add("Bench Press","73");
                } else {
                    upperBody.remove("Bench Press");
                }
                break;
            case R.id.chkPushup:
                if (checked) {
                    upperBody.add("Push Up", "447");
                } else {
                    upperBody.remove("Push Up");
                }
                break;
        }
    }



}