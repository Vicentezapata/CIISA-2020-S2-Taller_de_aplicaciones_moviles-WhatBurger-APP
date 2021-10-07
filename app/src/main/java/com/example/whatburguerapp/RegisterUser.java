package com.example.whatburguerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class RegisterUser extends AppCompatActivity {
    TextInputLayout til_name,til_mail,til_dateb,til_pass,til_rpass;
    Button btn_register;
    RadioGroup rg_diet;
    int mDay,mMonth,mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        //REFERENCIA WIDGETS
        til_name = findViewById(R.id.til_name);
        til_mail = findViewById(R.id.til_mail);
        til_dateb = findViewById(R.id.til_dateb);
        til_pass = findViewById(R.id.til_pass);
        til_rpass = findViewById(R.id.til_rpass);
        btn_register = findViewById(R.id.btn_register);
        rg_diet = findViewById(R.id.rg_diet);

        //EVENTO ONCLICK
        final Calendar calendar = Calendar.getInstance();
        mDay   = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH);
        mYear   = calendar.get(Calendar.YEAR);

        til_dateb.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog =  new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        if(month+1>=10){
                            if(day+1>10){
                                til_dateb.getEditText().setText(day+"/"+(month+1)+"/"+year);
                            }
                            else{
                                til_dateb.getEditText().setText("0"+day+"/"+(month+1)+"/"+year);
                            }
                        }
                        else{
                            if(day+1>10){
                                til_dateb.getEditText().setText(day+"/0"+(month+1)+"/"+year);
                            }
                            else{
                                til_dateb.getEditText().setText("0"+day+"/0"+(month+1)+"/"+year);
                            }
                        }

                    }
                }, mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        //ACCION DEL BOTON
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarDatos() == 0){
                    //Intent intent = new Intent(RegisterUser.class,DashBoard.class);
                    //startActivity(intent);
                }
            }
        });

    }

    //VALIDACION DE CAMPOS
    private int validarDatos(){
        Validate validate =  new Validate();
        int contandor = 0;
        String name  = til_name.getEditText().getText().toString();
        String dateb = til_dateb.getEditText().getText().toString();
        String mail  = til_mail.getEditText().getText().toString();
        String pass  = til_pass.getEditText().getText().toString();
        String rpass = til_rpass.getEditText().getText().toString();
        //VAIDAMOS QUE NOMBRE NO ESTE NULO
        if(validate.validarNulo(name)){
            //VAIDAMOS FORMATO DEL NOMBRE
            if(validate.validarNombre(name)){
                til_name.setError(null);
            }
            else{
                til_name.setError(getString(R.string.error_name_wrongformat));
                contandor++;
            }
        }
        else {
            til_name.setError(getString(R.string.error_text_null));
            contandor++;
        }
        //VAIDAMOS QUE CUMPLEAÑOS NO ESTE NULO
        if(validate.validarNulo(dateb)){
            til_dateb.setError(null);
        }
        else {
            til_dateb.setError(getString(R.string.error_text_null));
            contandor++;
        }
        //VAIDAMOS QUE MAIL NO ESTE NULO
        if(validate.validarNulo(mail)){
            //VAIDAMOS FORMATO DEL MAIL
            if(validate.validarCorreo(mail)){
                til_mail.setError(null);
            }
            else{
                til_mail.setError(getString(R.string.error_mail_wrongformat));
                contandor++;
            }
        }
        else {
            til_mail.setError(getString(R.string.error_text_null));
            contandor++;
        }
        //VAIDAMOS QUE PASS NO ESTE NULO
        if(validate.validarNulo(pass)){
            til_pass.setError(null);
        }
        else {
            til_pass.setError(getString(R.string.error_text_null));
            contandor++;
        }
        //VAIDAMOS QUE RPASS NO ESTE NULO
        if(validate.validarNulo(rpass)){
            til_rpass.setError(null);
        }
        else {
            til_rpass.setError(getString(R.string.error_text_null));
            contandor++;
        }
        //VAIDAMOS QUE PASS SEAN IGUALES
        if(rpass.equals(pass) && validate.validarNulo(rpass) && validate.validarNulo(pass)){
            til_rpass.setError(null);
            til_pass.setError(null);
        }
        else {
            if(validate.validarNulo(rpass) && validate.validarNulo(pass)){
                til_rpass.setError(getString(R.string.error_pass_notsame));
                til_pass.setError(getString(R.string.error_pass_notsame));
                contandor++;
            }
        }
        return contandor;
    }
}