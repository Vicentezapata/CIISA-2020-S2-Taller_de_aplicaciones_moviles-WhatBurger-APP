package com.example.whatburguerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout til_pass,til_user;
    private Button btnLogin,btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //REFERNCIAS DE WIDGET
        // Referencias WIDGETS es lo mismo que el getElementById de JS para HTML
        til_pass  = findViewById(R.id.til_password);
        til_user  = findViewById(R.id.til_user);
        btnLogin  = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = til_user.getEditText().getText().toString();
                String pass = til_pass.getEditText().getText().toString();
                Toast.makeText(MainActivity.this, "USER: "+user+" PASS"+pass, Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),RegisterUser.class);
                startActivity(intent);
            }
        });


    }
}