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

public class LowerBodyMenuActivity extends AppCompatActivity {

    private Button doneButton;
    private CheckBox squats, lunge, deadlift;
    private WorkoutPlan lowerBody;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private CollectionReference users;
    private ArrayList<String> selectedItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lower_body_menu);

        lowerBody = new WorkoutPlan("Lower Body");
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        users = db.collection("Users");

        doneButton = findViewById(R.id.btn_lower_done);
        squats = findViewById(R.id.chkSquats);
        deadlift = findViewById(R.id.chkDeadlift);
        lunge = findViewById(R.id.chkLunge);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItems = lowerBody.getItems();
                Map<String, Object> data = new HashMap<>();
                data.put("username", mAuth.getCurrentUser().getEmail());
                data.put("lowerBody", selectedItems);
                data.put("lowerBody_cal", lowerBody.getTotalCal());
                users.document(mAuth.getCurrentUser().getUid()).set(data, SetOptions.merge());
                openWorkoutActivity();
            }
        });

    }

    public void openWorkoutActivity() {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void onLowerBodyChkBoxStatChange(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.chkSquats:
                if (checked) {
                    lowerBody.add("Squats", "361");
                } else {
                    lowerBody.remove("Squats");
                }
                break;
            case R.id.chkLunge:
                if (checked) {
                    lowerBody.add("Lunge","73");
                } else {
                    lowerBody.remove("Lunge");
                }
                break;
            case R.id.chkDeadlift:
                if (checked) {
                    lowerBody.add("Deadlift", "447");
                } else {
                    lowerBody.remove("Deadlift");
                }
                break;
        }
    }

}