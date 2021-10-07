package com.example.whatburguerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.whatburguerapp.models.Usuario;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout til_pass,til_user;
    private Button btnLogin,btnRegister;

    private Switch sw_remember;

    //SHARED PREFERENCES
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;


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
        sw_remember = findViewById(R.id.sw_remember);

        //INICIALIZAMOS LAS VARIABLES SHARED
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //SI QUEREMOS CARGAR LAS SAHRED DE OTR APP
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences("Taller2021", Context.MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();

        //VERIDICAMOS SI EXISTE UN VALOR ALMACENADO
        String existMailUser = sharedPreferences.getString("mailUser","");
        String existpassUser = sharedPreferences.getString("passUser","");
        if(!existMailUser.equals("") && !existpassUser.equals("")){
            til_user.getEditText().setText(existMailUser);
            til_pass.getEditText().setText(existpassUser);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Validate validate = new Validate();
                String correo = til_user.getEditText().getText().toString();
                String pass   = til_pass.getEditText().getText().toString();
                if(validarDatos() == 0){
                    if(sw_remember.isChecked()){
                        //ALMACENAR VARIABLES EN SAHRED PREFERENCES
                        sharedEditor.putString("mailUser",correo);
                        sharedEditor.putString("passUser",pass);
                        //ESTE METODO ESPERA A QUE GUARDE LA LLAVE Y FLUJO SIGUE
                        sharedEditor.commit();
                        //ESTE METODONO ESPERA QUE SE ALLMACENE LA VARIABLE ES ASINCRONO
                        sharedEditor.apply();

                    }
                    Usuario usuario = new Usuario(correo,pass);
                    System.out.println(usuario.toString());

                    Intent intent = new Intent(view.getContext(),DashBoard.class);
                    intent.putExtra("mailUser",correo);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
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
    //VALIDACION DE CAMPOS
    private int validarDatos(){
        Validate validate =  new Validate();
        int contandor = 0;
        String mail = til_user.getEditText().getText().toString();
        String pass = til_pass.getEditText().getText().toString();
        if(validate.validarNulo(mail)){
            if(validate.validarCorreo(mail)){
                til_user.setError(null);
            }
            else{
                til_user.setError(getString(R.string.error_mail_wrongformat));
                contandor++;
            }
        }
        else {
            til_user.setError(getString(R.string.error_mail_null));
            contandor++;
        }
        if(validate.validarNulo(pass)){
            til_pass.setError(null);
        }
        else {
            til_pass.setError(getString(R.string.error_text_null));
            contandor++;
        }
        return contandor;
    }

}