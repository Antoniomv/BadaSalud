package com.vazquez.meliton.antonio.badasalud;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vazquez.meliton.antonio.badasalud.Fragmentos.RegistroFragmento;

public class MainActivity extends AppCompatActivity implements RegistroFragmento.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            cargarInicio();
        }
    }

    private void cargarInicio() {
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        RegistroFragmento registroFragmento = new RegistroFragmento();
        fragmentTransaction.add(R.id.fragment_container, registroFragmento);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
