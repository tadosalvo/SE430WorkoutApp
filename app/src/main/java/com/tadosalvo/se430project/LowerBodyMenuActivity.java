package com.tadosalvo.se430project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Text;

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

        TextView squatsReps = findViewById(R.id.editTextSquatsReps);
        TextView squatsSets = findViewById(R.id.editTextSquatsSets);
        TextView lungeReps = findViewById(R.id.editTextLungeReps);
        TextView lungeSets = findViewById(R.id.editTextLungeSets);
        TextView deadliftReps = findViewById(R.id.editTextDeadliftReps);
        TextView deadliftSets = findViewById(R.id.editTextDeadliftSets);


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItems = lowerBody.getItems();
                Map<String, Object> data = new HashMap<>();

                String squatsRepsStr = squatsReps.getText().toString();
                if (squatsRepsStr.equals("Enter Reps")) {
                    squatsRepsStr = "0";
                }

                String squatsSetsStr = squatsSets.getText().toString();
                if (squatsSetsStr.equals("Enter Sets")) {
                    squatsSetsStr = "0";
                }
                String finalSquatsRepsStr = squatsRepsStr;
                String finalSquatsSetsStr = squatsSetsStr;

                String lungeRepsStr = lungeReps.getText().toString();
                if (lungeRepsStr.equals("Enter Reps")) {
                    lungeRepsStr = "0";
                }

                String lungeSetsStr = lungeSets.getText().toString();
                if (lungeSetsStr.equals("Enter Sets")) {
                    lungeSetsStr = "0";
                }
                String finalLungeRepsStr = squatsRepsStr;
                String finalLungeSetsStr = squatsSetsStr;


                String deadliftRepsStr = deadliftReps.getText().toString();
                if (deadliftRepsStr.equals("Enter Reps")) {
                    deadliftRepsStr = "0";
                }

                String deadliftSetsStr = deadliftSets.getText().toString();
                if (deadliftSetsStr.equals("Enter Sets")) {
                    deadliftSetsStr = "0";
                }
                String finalDeadliftRepsStr = squatsRepsStr;
                String finalDeadliftSetsStr = squatsSetsStr;

                data.put("username", mAuth.getCurrentUser().getEmail());
                data.put("lowerBody", selectedItems);
                data.put("lowerBody_cal", lowerBody.getTotalCal());
                data.put("squatsReps", finalSquatsRepsStr);
                data.put("squatsSets", finalSquatsSetsStr);
                data.put("lungeReps", finalLungeRepsStr);
                data.put("lungeSets", finalLungeSetsStr);
                data.put("deadliftReps", finalDeadliftRepsStr);
                data.put("deadliftSets", finalDeadliftSetsStr);
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