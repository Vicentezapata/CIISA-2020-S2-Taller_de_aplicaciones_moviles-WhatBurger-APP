package com.example.whatburguerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.whatburguerapp.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity {
    private TextView tvWelcome;
    private Spinner sptypeBurguer;
    private RecyclerView rvBurguers;
    private ListView rvBurguersTest;
    private List<String> listTpBurguers;
    private List<String> listBurguers;
    private Button btnRoute;
    private String mailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);



        // Referencias WIDGETS es lo mismo que el getElementById de JS para HTML
        tvWelcome =  findViewById(R.id.tvWelcome);
        sptypeBurguer = findViewById(R.id.sp_typeBurguer);
        rvBurguers = (RecyclerView) findViewById(R.id.rv_burguers);
        rvBurguersTest = findViewById(R.id.lv_test);
        btnRoute = findViewById(R.id.btn_route);




        //METODO 1 PASAR VARIABLES POR INTENT
        Intent intent = getIntent();
        mailUser = intent.getStringExtra("mailUser");
        tvWelcome.setText(getString(R.string.dashboard_welcome) +" "+ mailUser);
        /*//METODO 2 POR MODELOS
        Usuario usuario = new Usuario();
        usuario.getCorreo();
        tvWelcome.setText(getString(R.string.dashboard_welcome) +" ");*/

        listTpBurguers = new ArrayList<String>();
        listTpBurguers.add("Carne rellena Queso");
        listTpBurguers.add("De lentejas");
        listTpBurguers.add("De Soya");
        listTpBurguers.add("Carne de vacuno");
        listTpBurguers.add("Carne de pollo");

        //_____________________________________SPINNER______________________________
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listTpBurguers);
        //ASIGNAR EL ADAPTER A NUESTRO SPINNER
        sptypeBurguer.setAdapter(arrayAdapter);


        listBurguers = new ArrayList<String>();
        listBurguers.add("Seta Burguer");
        listBurguers.add("Italian Burguer");
        listBurguers.add("Chilean Burguer");
        listBurguers.add("Zapallo Burguer");
        listBurguers.add("Lenteja Burguer");
        listBurguers.add("Hot Burguer");
        listBurguers.add("Not Burguer");

        //_____________________________________RECYCLER VIEW______________________________
        ArrayAdapter<String> arrayAdapterBurguers = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listBurguers);
        //ASIGNAR EL ADAPTER A NUESTRO SPINNER
        rvBurguersTest.setAdapter(arrayAdapterBurguers);



        btnRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MapsActivity.class);
                startActivity(intent);

            }
        });









    }
}