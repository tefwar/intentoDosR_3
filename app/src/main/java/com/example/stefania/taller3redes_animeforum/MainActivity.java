package com.example.stefania.taller3redes_animeforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private Button inicioSesion, registro;
    private FloatingActionButton config, reconectar;
    private static final String TAG = "MainActivity";
    private String mensajePantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicioSesion = (Button) findViewById(R.id.inicioSesion);
        registro = (Button) findViewById(R.id.registro_buttonregistro);
        config = (FloatingActionButton) findViewById(R.id.config);
        reconectar = (FloatingActionButton) findViewById(R.id.reconectar);

        inicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambiouno = new Intent(MainActivity.this,inicio.class);
                startActivity(cambiouno);
            }
        });

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambioConfig = new Intent(MainActivity.this,ConfiguracionRed.class);
                startActivity(cambioConfig);
            }
        });

        reconectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Communication.getInstance().reintentar();
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambiodos = new Intent(MainActivity.this,registro.class);
                startActivity(cambiodos);
            }
        });
    }

    private void mostrarMensajeToast(String mensajeAMostrar) {
        mensajePantalla = mensajeAMostrar;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), mensajePantalla, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void update(Observable observable, Object data) {
        String mensaje = (String) data;
        Log.d(TAG, "principal: " + observable.getClass() + " // " + mensaje);
        switch (mensaje) {
            case "no_conectado":
                mostrarMensajeToast("No se ha podido establecer conexión");
                break;
            case "conectado":
                mostrarMensajeToast("Conexión establecida");
                break;
            default:
                break;
        }
    }
}
