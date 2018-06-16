package com.vazquez.meliton.antonio.badasalud;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vazquez.meliton.antonio.badasalud.fragmentos.ContactoFragment;
import com.vazquez.meliton.antonio.badasalud.fragmentos.DesignCitaFragment;
import com.vazquez.meliton.antonio.badasalud.fragmentos.InicioFragment;
import com.vazquez.meliton.antonio.badasalud.fragmentos.ListaCitaFragment;
import com.vazquez.meliton.antonio.badasalud.fragmentos.ListaHospitalFragment;
import com.vazquez.meliton.antonio.badasalud.fragmentos.PanelUsuarioFragment;


public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ListaHospitalFragment.OnFragmentInteractionListener, InicioFragment.OnFragmentInteractionListener, ListaCitaFragment.OnFragmentInteractionListener,
        DesignCitaFragment.OnFragmentInteractionListener, PanelUsuarioFragment.OnFragmentInteractionListener,
        ContactoFragment.OnFragmentInteractionListener {

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TRAIGO LOS DATOS DEL LOGIN
        sharedPreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        //AL PULSAR EL BOTÓN FLOTANTE NOS MANDA AL FRAGMENTO DE AÑADIR CITA
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new DesignCitaFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenido, fragment)
                        .commit();
                FloatingActionButton mFloatingActionButton = findViewById(R.id.fab);
                mFloatingActionButton.hide();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //CARGO INICIO FRAGMENT COMO PRINCIPAL POR DEFECTO
        Fragment fragment = new InicioFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenido, fragment)
                .commit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //CARGO CADA FRAGMENTO CON SU LUGAR EN EL MENÚ

        if (id == R.id.nav_inicio) {
            Fragment fragment = new InicioFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenido, fragment)
                    .commit();
            FloatingActionButton mFloatingActionButton = findViewById(R.id.fab);
            mFloatingActionButton.show();
        } else if (id == R.id.nav_citas) {
            Fragment fragment = new ListaCitaFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenido, fragment)
                    .commit();
            FloatingActionButton mFloatingActionButton = findViewById(R.id.fab);
            mFloatingActionButton.show();
        } else if (id == R.id.nav_hospitales) {
            Fragment fragment = new ListaHospitalFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenido, fragment)
                    .commit();
            FloatingActionButton mFloatingActionButton = findViewById(R.id.fab);
            mFloatingActionButton.hide();
        } else if (id == R.id.nav_panel) {
            Fragment fragment = new PanelUsuarioFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenido, fragment)
                    .commit();
            FloatingActionButton mFloatingActionButton = findViewById(R.id.fab);
            mFloatingActionButton.hide();

        } else if (id == R.id.nav_contacto) {
            Fragment fragment = new ContactoFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenido, fragment)
                    .commit();
            FloatingActionButton mFloatingActionButton = findViewById(R.id.fab);
            mFloatingActionButton.hide();

        } else if (id == R.id.nav_cerrar) {
            cargarCerrar();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //MÉTODO PARA CERRAR SESIÓN
    private void cargarCerrar() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        finish();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
