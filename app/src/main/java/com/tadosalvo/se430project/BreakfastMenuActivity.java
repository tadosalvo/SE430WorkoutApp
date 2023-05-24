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

public class BreakfastMenuActivity extends AppCompatActivity {

    private Button doneButton;
    private CheckBox omelet,cereal,porridge,french_toast,bacon,pancake;
    private DietPlan breakfast;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private CollectionReference users;
    private ArrayList<String> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_menu);

        breakfast = new DietPlan("Breakfast");
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        users = db.collection("Users");

        doneButton = findViewById(R.id.btn_breakfast_done);
        omelet = findViewById(R.id.chkOmelet);
        cereal = findViewById(R.id.chkCereal);
        porridge = findViewById(R.id.chkPorridge);
        french_toast = findViewById(R.id.chkFrenchToast);
        bacon = findViewById(R.id.chkBacon);
        pancake = findViewById(R.id.chkPancake);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItems = breakfast.getItems();
                Map<String, Object> data = new HashMap<>();
                data.put("username", mAuth.getCurrentUser().getEmail());
                data.put("breakfast", selectedItems);
                data.put("breakfast_cal", breakfast.getTotalCal());
                users.document(mAuth.getCurrentUser().getUid()).set(data, SetOptions.merge());
                openDietActivity();
            }
        });
    }

    public void openDietActivity() {
        Intent intent = new Intent(this, DietActivity.class);
        startActivity(intent);
    }

    public void onBreakfastChkBoxStatChange(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.chkOmelet:
                if(checked){
                    breakfast.add("Omelet","94");
                }else{
                    breakfast.remove("Omelet");
                }
                break;
            case R.id.chkCereal:
                if(checked){
                    breakfast.add("Cereal","207");
                }else{
                    breakfast.remove("Cereal");
                }
                break;
            case R.id.chkPorridge:
                if(checked){
                    breakfast.add("Porridge","125");
                }else{
                    breakfast.remove("Porridge");
                }
                break;
            case R.id.chkFrenchToast:
                if(checked){
                    breakfast.add("French Toast","149");
                }else{
                    breakfast.remove("French Toast");
                }
                break;
            case R.id.chkBacon:
                if(checked){
                    breakfast.add("Bacon","129");
                }else{
                    breakfast.remove("Bacon");
                }
                break;
            case R.id.chkPancake:
                if(checked){
                    breakfast.add("Pancake","64");
                }else{
                    breakfast.remove("Pancake");
                }
                break;
        }
    }
}