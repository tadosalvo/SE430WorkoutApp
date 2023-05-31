package com.tadosalvo.se430project;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Scanner;

public class UsingContact {
    public static void main(String[] args) {
        PageForContact contactPage = new PageForContact();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter the message: ");
        String message = scanner.nextLine();

        System.out.print("Enter name");
        String name = scanner.nextLine();

        Contact contact = new Contact(name, email, message);
        contactPage.addContact(contact);

        System.out.println("Thanks for contacting us! We really appreciate any feedback or suggestions you may have");

        List<Contact> allContacts = contactPage.getAllContacts(); // Retrieving the contacts
        System.out.println("All Contacts:");
        for (Contact c : allContacts) {
            System.out.println("Name: " + c.getName());
            System.out.println("Email: " + c.getEmail());
            System.out.println("Message: " + c.getMessage());
            System.out.println();
        }
    }
}
