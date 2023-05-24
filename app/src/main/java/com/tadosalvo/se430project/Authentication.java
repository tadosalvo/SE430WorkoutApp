package com.tadosalvo.se430project;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Authentication {

    private FirebaseAuth mAuth;
    private Context context;

    public Authentication(Context context){
        mAuth = FirebaseAuth.getInstance();
        this.context = context;
    }

    public void login(String username, String password){
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Login successful.",
                                    Toast.LENGTH_SHORT).show();
                            openHomeActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signup(String username, String password){
        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Account successfully registered.",
                                    Toast.LENGTH_SHORT).show();
                            openMainActivity();
                        } else {
                            // If sign up fails, display a message to the user.
                            Toast.makeText(context, "Signup failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void openMainActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void openHomeActivity() {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }
}
