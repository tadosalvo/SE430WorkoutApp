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

public class CoreMenuActivity extends AppCompatActivity {

    private Button doneButton;
    private CheckBox curlUp, sitUp, sideBridge;
    private WorkoutPlan core;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private CollectionReference users;
    private ArrayList<String> selectedItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core_menu);

        core = new WorkoutPlan("Core");
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        users = db.collection("Users");

        doneButton = findViewById(R.id.btn_core_done);
        curlUp = findViewById(R.id.chkCurlUp);
        sitUp = findViewById(R.id.chkSideBridge);
        sideBridge = findViewById(R.id.chkSitUp);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItems = core.getItems();
                Map<String, Object> data = new HashMap<>();
                data.put("username", mAuth.getCurrentUser().getEmail());
                data.put("core", selectedItems);
                data.put("core_cal", core.getTotalCal());
                users.document(mAuth.getCurrentUser().getUid()).set(data, SetOptions.merge());
                openWorkoutActivity();
            }
        });

    }

    public void openWorkoutActivity() {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void onCoreChkBoxStatChange(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.chkCurlUp:
                if (checked) {
                    core.add("Curl Up", "361");
                } else {
                    core.remove("Curl Up");
                }
                break;
            case R.id.chkSitUp:
                if (checked) {
                    core.add("Sit Up","73");
                } else {
                    core.remove("Sit Up");
                }
                break;
            case R.id.chkSideBridge:
                if (checked) {
                    core.add("Side Bridge", "447");
                } else {
                    core.remove("Side Bridge");
                }
                break;
        }

    }


}