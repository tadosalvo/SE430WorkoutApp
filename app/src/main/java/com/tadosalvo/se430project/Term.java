package com.tadosalvo.se430project;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class Term {
    private String term;
    private String about;

    public Term(String term, String about) {
        this.term = term;
        this.about = about;
    }

    public String getTerm() { //These are the getters and setters
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
