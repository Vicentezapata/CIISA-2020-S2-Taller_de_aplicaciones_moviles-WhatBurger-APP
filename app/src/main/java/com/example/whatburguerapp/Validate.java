package com.example.whatburguerapp;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Validate {
    public boolean validarNombre(String nombre) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        return pattern.matcher(nombre).matches();
    }

    public boolean validarCorreo(String mail){
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }
    public boolean validarNulo(String texto){
        if(texto.equals("")){
            return false;
        }
        else{
            return true;
        }
    }
}
