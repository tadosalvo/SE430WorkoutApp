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

public class DinnerMenuActivity extends AppCompatActivity {

    private Button doneButton;
    private CheckBox meatloaf,chicken_noodle_soup,salmon,pasta,pizza,udon_noodle;
    private DietPlan dinner;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private CollectionReference users;
    private ArrayList<String> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner_menu);

        dinner = new DietPlan("Dinner");
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        users = db.collection("Users");

        doneButton = findViewById(R.id.btn_dinner_done);
        meatloaf = findViewById(R.id.chkMeatloaf);
        chicken_noodle_soup = findViewById(R.id.chkChickenNoodleSoup);
        salmon = findViewById(R.id.chkSalmon);
        pasta = findViewById(R.id.chkPasta);
        pizza = findViewById(R.id.chkPizza);
        udon_noodle = findViewById(R.id.chkUdonNoodle);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItems = dinner.getItems();
                Map<String, Object> data = new HashMap<>();
                data.put("username", mAuth.getCurrentUser().getEmail());
                data.put("dinner", selectedItems);
                data.put("dinner_cal", dinner.getTotalCal());
                users.document(mAuth.getCurrentUser().getUid()).set(data, SetOptions.merge());
                openDietActivity();
            }
        });
    }

    public void openDietActivity() {
        Intent intent = new Intent(this, DietActivity.class);
        startActivity(intent);
    }

    public void onDinnerChkBoxStatChange(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.chkMeatloaf:
                if(checked){
                    dinner.add("Meatloaf","149");
                }else{
                    dinner.remove("Meatloaf");
                }
                break;
            case R.id.chkChickenNoodleSoup:
                if(checked){
                    dinner.add("Chicken Noodle Soup","87");
                }else{
                    dinner.remove("Chicken Noodle Soup");
                }
                break;
            case R.id.chkSalmon:
                if(checked){
                    dinner.add("Salmon","412");
                }else{
                    dinner.remove("Salmon");
                }
                break;
            case R.id.chkPasta:
                if(checked){
                    dinner.add("Pasta","131");
                }else{
                    dinner.remove("Pasta");
                }
                break;
            case R.id.chkPizza:
                if(checked){
                    dinner.add("Pizza","570");
                }else{
                    dinner.remove("Pizza");
                }
                break;
            case R.id.chkUdonNoodle:
                if(checked){
                    dinner.add("Udon Noodle","310");
                }else{
                    dinner.remove("Udon Noodle");
                }
                break;
        }
    }
}