package com.vazquez.meliton.antonio.badasalud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

//extendemos a Activity para poder usar otros themes en el manifest
public class SplashScreen extends Activity {

    Animation rotacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //INICIO LA ROTACIÓN DE LA IMAGEN
        rotarImagen();

        //CREAO UN HANDLER PARA INDICARLE EL TIEMPO QUE QUEREMOS QUE DURE ANTES
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
        }, 1550);
    }

    //MÉTODO ROTACIÓN IMAGEN INICIAL
    private void rotarImagen() {
        ImageView imagen = findViewById(R.id.imagenRotar);
        rotacion = AnimationUtils.loadAnimation(this, R.anim.rotar);
        imagen.setAnimation(rotacion);
    }
}
