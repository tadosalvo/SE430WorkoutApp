package com.tadosalvo.se430project;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Category {
    public static void main(String[] args) {
        Encyclopedia encyclopedia = new Encyclopedia();

        Term enter1 = new Term("Weightlifting", "Weightlifting is one of the key ways to build upper body strength."); // Added terms
        Term enter2 = new Term("Cardio", "Cardio is important for improving the flow of oxygen throughout your body.");
        encyclopedia.addTerm(enter1);
        encyclopedia.addTerm(enter2);

        Term retrievedEnter = encyclopedia.getEnterByTerm("Weightlifting"); // Retrieve anything entered by the term
        if (retrievedEnter != null) {
            System.out.println("Term: " + retrievedEnter.getTerm());
            System.out.println("About: " + retrievedEnter.getAbout());
        } else {
            System.out.println("Term not located.");
        }
    }
}