package com.tadosalvo.se430project;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class PageForContact {
    private List<Contact> thecontacts;

    public PageForContact() {
        this.thecontacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        thecontacts.add(contact);
    }

    public List<Contact> getAllContacts() {
        return thecontacts;
    }
}
