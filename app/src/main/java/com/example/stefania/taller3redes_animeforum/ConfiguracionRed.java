package com.example.stefania.taller3redes_animeforum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConfiguracionRed extends AppCompatActivity {

    Button aceptar, cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

        aceptar = (Button) findViewById(R.id.aceptar_config);
        cancelar = (Button) findViewById(R.id.cancelar_config);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ipEd = (EditText) findViewById(R.id.ipconfig);
                EditText portEd = (EditText) findViewById(R.id.port_config);
                String ip = ipEd.getText().toString();
                String port = portEd.getText().toString();
                if (!ip.equals("") && !port.equals("")) {
                    Communication.getInstance().setIp(ip);
                    Communication.getInstance().setPuerto(Integer.parseInt(port));
                    Communication.getInstance().reintentar();
                    finish();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}