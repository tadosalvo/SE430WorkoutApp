package com.tadosalvo.se430project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText signup_username, signup_password;
    private Button signupButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_username = findViewById(R.id.signup_username);
        signup_password = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.btn_Signup);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = String.valueOf(signup_username.getText());
                String password = String.valueOf(signup_password.getText());

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(SignupActivity.this, "Username can't be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignupActivity.this, "Password can't be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Authentication auth = new Authentication(SignupActivity.this);
                auth.signup(username, password);
            }
        });
    }
}