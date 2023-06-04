package com.example.project_login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class registerActivity extends AppCompatActivity {
    EditText username, password, phone;
    Button signup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        signup = findViewById(R.id.btnsignup);
        DB = new DBHelper(this);


        signup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String pho =  phone.getText().toString();
                if (user.equals("") || pass.equals("") || pho.equals(""))
                    Toast.makeText(registerActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUser = DB.checkUsername(user);
                    if (!checkUser) {
                        Boolean insert = DB.insertData(user, pass, pho);
                        if (insert) {
                            Toast.makeText(registerActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), profileActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(registerActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(registerActivity.this, "Already exists! please sign in", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}