package com.vazquez.meliton.antonio.badasalud;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vazquez.meliton.antonio.badasalud.controladores.UsuarioController;


public class RegistroActivity extends AppCompatActivity {

    EditText nombre, apellidos, telefono, email, password;
    Button registrarse;
    private UsuarioController usuarioController;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Doy valor a las variables
        nombre = findViewById(R.id.et_nombre);
        apellidos = findViewById(R.id.et_apellidos);
        telefono = findViewById(R.id.et_telefono);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);

        registrarse = findViewById(R.id.boton_registro);

        //creamos método para el botón
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insertamos usuario
                nuevoUsuario();

                //Limpiamos el formulario
                limpiarRegistro();
            }
        });

    }

    private void nuevoUsuario() {

     //importamos el controllador
     usuarioController = new UsuarioController(this, view );
    //insertamos valores y los transformamos en String
    String nombreGuardar = nombre.getText().toString();
    String apellidosGuardar = apellidos.getText().toString();
    String telefonoGuardar = telefono.getText().toString();
    String emailGuardar = email.getText().toString();
    String passwordGuardar = password.getText().toString();

    //Evitamos que se manden datos vacíos
    Boolean ok = true;
    Boolean entrarLogin = false;
        if ((nombreGuardar.isEmpty()) || (apellidosGuardar.isEmpty()) || (telefonoGuardar.isEmpty()) || (emailGuardar.isEmpty()) || (passwordGuardar.isEmpty())) {

        if (nombreGuardar.isEmpty()) {
            nombre.setError("El nombre no puede estar vacío");
        }
        if (apellidosGuardar.isEmpty()) {
            apellidos.setError("El apellidos no puede estar vacío");
        }
        if (telefonoGuardar.isEmpty()) {
            telefono.setError("El telefono no puede estar vacío");
        }
        if (emailGuardar.isEmpty()) {
            email.setError("El email no puede estar vacío");
        }
        if (passwordGuardar.isEmpty()) {
            password.setError("El password no puede estar vacío");
        }
        ok = false;
    }

        if (ok) {
        usuarioController.nuevoUsuario(nombreGuardar, apellidosGuardar, telefonoGuardar, emailGuardar, passwordGuardar);
        entrarLogin = true;
    }
        if (entrarLogin) {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        };
        handler.postDelayed(runnable, 1500);
    }
}

    //método para limpiar registro
    private void limpiarRegistro() {
        nombre.setText("");
        apellidos.setText("");
        telefono.setText("");
        email.setText("");
        password.setText("");
    }

    //asignamos un ciclo de vida
    @Override
    public void
    onStart(){
        super.onStart();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void
    onStop() {
        super.onStop();

    }

    @Override
    public void
    onDestroy() {
        super.onDestroy();

    }

}
