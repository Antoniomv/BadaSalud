package com.vazquez.meliton.antonio.badasalud;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.vazquez.meliton.antonio.badasalud.constantes.Constantes;
import com.vazquez.meliton.antonio.badasalud.controladores.LoginController;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    //DECLARO VARIABLES
    public static final String MyPREFERENCES = "MyPrefs", id_key = "idKey";
    private static String URL = Constantes.LOGIN;
    SharedPreferences sharedPreferences;
    Button loginButton;
    EditText loginEmail, loginPassword;
    TextView registro;
    JsonObjectRequest jsonObjectRequest;
    JsonObject jsonObject;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //CARGO VISTAS
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        registro = findViewById(R.id.txt_registro);
        loginButton = findViewById(R.id.loginButton);

        //EJECUTA EL LOGIN
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRequest();
            }
        });

        //ENVIA AL ACTIVITY REGISTRO
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        //GUARDAMOS SESIÓN PARA QUE SIEMPRE QUE ABRAMOS LA APLICACIÓN NO NECESITEMOS LOGUEARNOS A NO SER QUE CAMBIEMOS DE CUENTA, SIEMPRE QUE USEMOS NUESTRO MÓVIL
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(id_key)) {
            finish();
            Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
            startActivity(intent);
        }
        super.onResume();
    }

    private void loginRequest() {
        //TRAGIO LOS DATOS A COMPARAR
        String emailText, passwordText;
        emailText = loginEmail.getText().toString();
        passwordText = loginPassword.getText().toString();

        //CARGO CONTROLADOR
        LoginController login = new LoginController(emailText, passwordText, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                        //GUARDO DATOS EN SHAREDPREFERENCES
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(id_key, jsonObject1.getString("id"));
                        editor.apply();

                        finish();
                        //SI SALE TODO BIEN PASAMOS A LA ACTIVIDAD PRINCIPAL
                        startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "email or password is incorrect", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        //EVITO QUE ESTÉ VACÍO
        if (emailText.isEmpty() || passwordText.isEmpty()) {
            loginEmail.setError("Introduce el Email");
            loginPassword.setError("Introduce una contraseña");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
            loginEmail.setError("Email Incorrecto");
        else if (emailText.length() < 8) loginPassword.setError("Password Incorrecto");
        else Volley.newRequestQueue(this).add(login);

    }

    public void showSnackbar(String stringSnackbar) {
        Snackbar.make(findViewById(android.R.id.content), stringSnackbar, Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }

}
