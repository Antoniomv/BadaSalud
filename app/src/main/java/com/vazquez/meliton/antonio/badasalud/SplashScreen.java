package com.vazquez.meliton.antonio.badasalud;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//extendemos a Activity para poder usar otros themes en el manifest
public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //CREAMOS UN HANDLER PARA INDICARLE EL TIEMPO QUE QUEREMOS QUE DURE ANTES
        //ANTES DE LLEVARNOS AL SIGUIENTE ACTIVITY
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //CON UN INTENT ENVIO DEL SPLASHSCREEN AL MENÚ INICIAL
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                //usamos finish para evitar volver a la pantalla de carga
                finish();
            }
        },3000);
    }
}
