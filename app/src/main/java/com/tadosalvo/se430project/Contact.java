package com.tadosalvo.se430project;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Contact {
    private String InfoBox;
    private String UserName;
    private String emailAddress;

    public Contact(String name, String email, String message) {
        this.UserName = name;
        this.emailAddress = email;
        this.InfoBox = message;
    }
    public String getName() {
        return UserName;
    } //These are the getters and setters for the name and email address

    public void setName(String name) {
        this.UserName = name;
    }

    public String getEmail() {
        return emailAddress;
    }

    public void setEmail(String email) {
        this.emailAddress = email;
    }

    public String getMessage() {
        return InfoBox;
    }

    public void setMessage(String message) {
        this.InfoBox = message;
    }
}