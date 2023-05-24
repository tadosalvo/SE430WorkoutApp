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

public class LunchMenuActivity extends AppCompatActivity {

    private Button doneButton;
    private CheckBox sandwich,salad,burger,meatballs,burritos,soup;
    private DietPlan lunch;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private CollectionReference users;
    private ArrayList<String> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_menu);

        lunch = new DietPlan("Lunch");
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        users = db.collection("Users");

        doneButton = findViewById(R.id.btn_lunch_done);
        sandwich = findViewById(R.id.chkSandwich);
        salad = findViewById(R.id.chkSalad);
        burger = findViewById(R.id.chkBurger);
        meatballs = findViewById(R.id.chkMeatballs);
        burritos = findViewById(R.id.chkBurritos);
        soup = findViewById(R.id.chkSoup);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItems = lunch.getItems();
                Map<String, Object> data = new HashMap<>();
                data.put("username", mAuth.getCurrentUser().getEmail());
                data.put("lunch", selectedItems);
                data.put("lunch_cal", lunch.getTotalCal());
                users.document(mAuth.getCurrentUser().getUid()).set(data, SetOptions.merge());
                openDietActivity();
            }
        });
    }

    public void openDietActivity() {
        Intent intent = new Intent(this, DietActivity.class);
        startActivity(intent);
    }

    public void onLunchChkBoxStatChange(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.chkSandwich:
                if(checked){
                    lunch.add("Sandwich","361");
                }else{
                    lunch.remove("Sandwich");
                }
                break;
            case R.id.chkSalad:
                if(checked){
                    lunch.add("Salad","200");
                }else{
                    lunch.remove("Salad");
                }
                break;
            case R.id.chkBurger:
                if(checked){
                    lunch.add("Burger","354");
                }else{
                    lunch.remove("Burger");
                }
                break;
            case R.id.chkMeatballs:
                if(checked){
                    lunch.add("Meatballs","284");
                }else{
                    lunch.remove("Meatballs");
                }
                break;
            case R.id.chkBurritos:
                if(checked){
                    lunch.add("Burritos","447");
                }else{
                    lunch.remove("Burritos");
                }
                break;
            case R.id.chkSoup:
                if(checked){
                    lunch.add("Soup","73");
                }else{
                    lunch.remove("Soup");
                }
                break;
        }
    }
}