package com.example.whatburguerapp.models;

public class Usuario {
    private String correo;
    private String pass;

    public Usuario(String correo, String pass) {
        this.correo = correo;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "correo='" + correo + '\'' +
                ", pass=" + pass +
                '}';
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
