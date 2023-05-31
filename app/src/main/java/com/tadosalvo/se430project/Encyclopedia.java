package com.tadosalvo.se430project;

import java.util.ArrayList;

public class Encyclopedia {

    private ArrayList<Term> terms = new ArrayList<>();

    public Encyclopedia() {
        setTerms();
    }

    private void setTerms(){
        Term term1 = new Term("Diet Plan", "A program that helps with your dieting needs");
        Term term2 = new Term("Workout Program", "A program set to help with your workouts");
        Term term3 = new Term("Weightlifting", "Weightlifting is one of the key ways to build upper body strength.");
        Term term4 = new Term("Cardio", "Cardio is important for improving the flow of oxygen throughout your body.");
        terms.add(term1);
        terms.add(term2);
        terms.add(term3);
        terms.add(term4);
    }

    public ArrayList<Term> getEncyclopedia(){
        return terms;
    }
}
