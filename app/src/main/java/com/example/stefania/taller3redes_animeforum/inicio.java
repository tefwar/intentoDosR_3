package com.example.stefania.taller3redes_animeforum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;


public class inicio extends AppCompatActivity implements Observer {

    private static final String TAG = "inicio";
    private String mensajePantalla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Communication.getInstance().addObserver(this);
        mensajePantalla = "...";
        BotonLogin();
        BotonRegistrarse();


    }


    private void BotonRegistrarse() {
        Button irRegistro = (Button) findViewById(R.id.irSesion_registro);
        irRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambiouno = new Intent(inicio.this,registro.class);
                startActivity(cambiouno);
                finish();
            }
        });
    }

    private void BotonLogin() {
        Button btnLogin = (Button) findViewById(R.id.iniciar);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usuario = (EditText) findViewById(R.id.usuario_sesion);
                EditText contrasena = (EditText) findViewById(R.id.contrasena_sesion);
                String user = usuario.getText().toString();
                String pass = contrasena.getText().toString();
                if (!user.equals("") && !pass.equals("")) {
                    Communication.getInstance().enviar("login_req:" + user + ":" + pass);
                    Log.d(TAG, "Se envió: login_req:" + user + ":" + pass);
                    usuario.setHintTextColor(Color.BLACK);
                    contrasena.setHintTextColor(Color.BLACK);
                } else {
                    usuario.setHintTextColor(Color.RED);
                    contrasena.setHintTextColor(Color.RED);
                    usuario.setHighlightColor(Color.RED);
                    contrasena.setHighlightColor(Color.RED);
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
