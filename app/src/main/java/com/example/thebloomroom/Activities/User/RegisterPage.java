package com.example.thebloomroom.Activities.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thebloomroom.Database.DatabaseHelper;
import com.example.thebloomroom.R;
import com.example.thebloomroom.Classes.User;

public class RegisterPage extends AppCompatActivity {
    private Button btnAdd;
    private EditText userName, userUserName, userEmail, userPass, userRePass;
    private TextView loginLink;
    private int id;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        btnAdd = findViewById(R.id.btnAddUser);
        userName = findViewById(R.id.user_name);
        userUserName = findViewById(R.id.user_username);
        userEmail = findViewById(R.id.user_email);
        userPass = findViewById(R.id.password);
        userRePass = findViewById(R.id.re_password);
        loginLink = findViewById(R.id.login_link);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(RegisterPage.this);
                User user = null;
                try {
                    String pass = userPass.getText().toString();
                    String repass = userRePass.getText().toString();
                    if (pass.equals(repass)) {
                        user = new User(-1, userName.getText().toString(), "Admin", userUserName.getText().toString(), userEmail.getText().toString(), userPass.getText().toString());
                        //todo once you have created a admin user change th above code to the commented one and delete that one
                        //user = new User(-1, userName.getText().toString(), "User", userUserName.getText().toString(), userEmail.getText().toString(), userPass.getText().toString());

                        boolean success = databaseHelper.insertUser(user);
                        if (success) {
                            Toast.makeText(RegisterPage.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent login = new Intent(RegisterPage.this, LoginPage.class);
                            startActivity(login);
                        } else {
                            Toast.makeText(RegisterPage.this, "Error Inserting Data", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(RegisterPage.this, "Re-typed password does not match password", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(RegisterPage.this, "Fields Cannot be Left Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }
    
}