package com.example.stefania.taller3redes_animeforum;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

public class registro extends AppCompatActivity implements Observer{

        private static final String TAG = "registro";

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Communication.getInstance().addObserver(this);
        irLogin();
        registro();

        }

        public void irLogin (){
                Button irLog = (Button) findViewById(R.id.irSesion_registro);
                irLog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent cambiouno = new Intent(registro.this,inicio.class);
                                startActivity(cambiouno);
                                finish();
                        }
                });
        }

        public void registro(){
                Button registrarse = (Button) findViewById(R.id.registro_buttonregistro);
                registrarse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                EditText usuario = (EditText) findViewById(R.id.usuario_sesion);
                                EditText contrasena = (EditText) findViewById(R.id.contrasena_sesion);
                                EditText correo = (EditText) findViewById(R.id.correo_registro);

                                String user = usuario.getText().toString();
                                String pass = contrasena.getText().toString();
                                String email = correo.getText().toString();
                                if (!user.equals("") && !pass.equals("")) {
                                        Communication.getInstance().enviar("signup_req:" + user + ":" + pass);
                                        usuario.setHintTextColor(Color.BLACK);
                                        contrasena.setHintTextColor(Color.BLACK);
                                        correo.setHintTextColor(Color.BLACK);
                                        Intent regreso = new Intent();
                                        regreso.putExtra("usuario_reg", user);
                                        regreso.putExtra("contrasena_reg", pass);
                                        regreso.putExtra("correo_reg", email);
                                        setResult(1, regreso);
                                } else {
                                        usuario.setHintTextColor(Color.RED);
                                        contrasena.setHintTextColor(Color.RED);
                                        correo.setHintTextColor(Color.RED);
                                }
                        }
                });

        }

        @Override
        public void update(Observable observable, Object data) {
                String mensaje = (String) data;
                Log.d(TAG, "registrarse: " + observable.getClass() + " // " + mensaje);
                switch (mensaje) {
                        case "usuario_existe":
                                runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                                Toast.makeText(getApplicationContext(), "El usuario ya existe :c", Toast.LENGTH_LONG).show();
                                        }
                                });
                                break;
                        case "usuario_registrado":
                                runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                                Toast.makeText(getApplicationContext(), "Registro exitoso c:", Toast.LENGTH_LONG).show();
                                        }
                                });
                                finish();
                                break;
                        default:
                                break;
                }
        }
}
