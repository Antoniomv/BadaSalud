package com.vazquez.meliton.antonio.badasalud;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.vazquez.meliton.antonio.badasalud.controladores.RegistroController;

import org.json.JSONException;
import org.json.JSONObject;


public class RegistroActivity extends AppCompatActivity {

    EditText nombre, apellidos, telefono, email, password;
    TextView login;
    Button registrarse;
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

        login = findViewById(R.id.txt_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

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

    //insertamos valores y los transformamos en String
    String nombreGuardar = nombre.getText().toString();
    String apellidosGuardar = apellidos.getText().toString();
    String telefonoGuardar = telefono.getText().toString();
    String emailGuardar = email.getText().toString();
    String passwordGuardar = password.getText().toString();

    //llamamos al controlador y enviamos datos
        RegistroController user = new RegistroController(nombreGuardar, apellidosGuardar, telefonoGuardar, emailGuardar, passwordGuardar, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        Toast.makeText(getApplicationContext(), "Usuario Registrado Correctamente", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }else{
                        String error = jsonObject.getString("error");
                        if(error.equals("EMAIL_DUPLICATE")){
                            email.setError("El email ya existe en la base de datos");
                            Toast.makeText(getApplicationContext(),"El email ya existe en la base de datos", Toast.LENGTH_LONG).show();
                        }else if(error.equals("ERROR_FATAL")){
                            Toast.makeText(getApplicationContext(), "Sin conexion: vuelva a intentarlo más tarde", Toast.LENGTH_LONG).show();
                        }else if(error.equals("TELEFONO_DUPLICATE")){
                            telefono.setError("El teléfono ya existe en la base de datos");
                            Toast.makeText(getApplicationContext(),"El teléfono ya existe en la base de datos", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //Evitamos que se manden datos vacíos
        if(nombreGuardar.isEmpty()) nombre.setError("Introduce un nombre");
        else if(apellidosGuardar.isEmpty()) apellidos.setError("Introduce apellidos");
        else if(telefonoGuardar.isEmpty() || telefonoGuardar.length() < 9 || telefonoGuardar.length() > 9) telefono.setError("número de teléfono incorrecto");
        else if(emailGuardar.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailGuardar).matches()) email.setError("email incorrecto");
        else if(passwordGuardar.isEmpty() || passwordGuardar.length() < 5) password.setError("la contraseña debe tener 5 caracteres mínimo");
        else Volley.newRequestQueue(this).add(user);
}

    //método para limpiar registro
    private void limpiarRegistro() {
        nombre.setText("");
        apellidos.setText("");
        telefono.setText("");
        email.setText("");
        password.setText("");
    }

}
