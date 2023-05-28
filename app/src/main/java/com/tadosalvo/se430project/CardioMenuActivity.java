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

public class CardioMenuActivity extends AppCompatActivity {

    private Button doneButton;
    private CheckBox run, rows, cycling;
    private WorkoutPlan cardio;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private CollectionReference users;
    private ArrayList<String> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio_menu);

        cardio = new WorkoutPlan("Cardio");
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        users = db.collection("Users");

        doneButton = findViewById(R.id.btn_cardio_done);
        run = findViewById(R.id.chkRunTime);
        rows = findViewById(R.id.chkRows);
        cycling = findViewById(R.id.chkCyclingTime);

        TextView runReps = findViewById(R.id.editTextRunReps);
        TextView runSets = findViewById(R.id.editTextRunSets);
        TextView rowsReps = findViewById(R.id.editTextRowsReps);
        TextView rowsSets = findViewById(R.id.editTextRowsSets);
        TextView cyclingReps = findViewById(R.id.editTextCyclingReps);
        TextView cyclingSets = findViewById(R.id.editTextCyclingSets);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItems = cardio.getItems();
                Map<String, Object> data = new HashMap<>();

                String runRepsStr = runReps.getText().toString();
                if (runRepsStr.equals("Enter Reps")) {
                    runRepsStr = "0";
                }
                String runSetsStr = runSets.getText().toString();
                if (runSetsStr.equals("Enter Sets")) {
                    runSetsStr = "0";
                }
                String rowsRepsStr = rowsReps.getText().toString();
                if (rowsRepsStr.equals("Enter Reps")) {
                    rowsRepsStr = "0";
                }
                String rowsSetsStr = rowsSets.getText().toString();
                if (rowsSetsStr.equals("Enter Sets")) {
                    rowsSetsStr = "0";
                }
                String cyclingRepsStr = cyclingReps.getText().toString();
                if (cyclingRepsStr.equals("Enter Reps")) {
                    cyclingRepsStr = "0";
                }
                String cyclingSetsStr = cyclingSets.getText().toString();
                if (cyclingSetsStr.equals("Enter Sets")) {
                    cyclingSetsStr = "0";
                }


                data.put("username", mAuth.getCurrentUser().getEmail());
                data.put("cardio", selectedItems);
                data.put("cardio_cal", cardio.getTotalCal());
                data.put("runReps", runRepsStr );
                data.put("runSets", runSetsStr);
                data.put("rowsReps", rowsRepsStr);
                data.put("rowsSets", rowsSetsStr);
                data.put("cyclingReps", cyclingRepsStr);
                data.put("cyclingSets", cyclingSetsStr);

                users.document(mAuth.getCurrentUser().getUid()).set(data, SetOptions.merge());
                openWorkoutActivity();
            }
        });

    }

    public void openWorkoutActivity() {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void onCardioChkBoxStatChange(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.chkRunTime:
                if (checked) {
                    cardio.add("Run", "361");
                } else {
                    cardio.remove("Run");
                }
                break;
            case R.id.chkRows:
                if (checked) {
                    cardio.add("Rows","73");
                } else {
                    cardio.remove("Rows");
                }
                break;
            case R.id.chkCyclingTime:
                if (checked) {
                    cardio.add("Cycling", "447");
                } else {
                    cardio.remove("Cycling");
                }
                break;
        }
    }


}