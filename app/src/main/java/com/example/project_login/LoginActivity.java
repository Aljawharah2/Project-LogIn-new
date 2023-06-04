package com.example.project_login;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username, password, Phone;
    Button signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);

        signin = findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);


        signin.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            if (user.equals("") || pass.equals(""))
                Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean check = DB.checkUsernamePassword(user, pass);
                if (check) {
                    Toast.makeText(LoginActivity.this, "Log in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), profileActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}