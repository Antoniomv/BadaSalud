package com.vazquez.meliton.antonio.badasalud;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.vazquez.meliton.antonio.badasalud.constantes.Constantes;
import com.vazquez.meliton.antonio.badasalud.constantes.VolleySingleton;
import com.vazquez.meliton.antonio.badasalud.controladores.LoginController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    public static final String MyPREFERENCES = "MyPrefs", id_key = "idKey";
    SharedPreferences sharedPreferences;
    Button loginButton;
    EditText loginEmail, loginPassword;
    TextView registro;
    private static String URL  = Constantes.LOGIN;
    private Snackbar snackbar;
    JsonObjectRequest jsonObjectRequest;
    JsonObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        registro = findViewById(R.id.txt_registro);
        loginButton =findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRequest();
            }
        });

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
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(id_key)){
            finish();
            Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
            startActivity(intent);
        }
        super.onResume();
    }

    private void loginRequest(){
        String emailText, passwordText;
        emailText = loginEmail.getText().toString();
        passwordText = loginPassword.getText().toString();

        LoginController login = new LoginController(emailText,passwordText, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(id_key,jsonObject1.getString("id"));
                        editor.apply();

                        finish();
                        startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"email or password is incorrect", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        if(emailText.isEmpty() || passwordText.isEmpty()){
            loginEmail.setError("Introduce el Email");
            loginPassword.setError("Introduce una contraseña");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) loginEmail.setError("Email Incorrecto");
        else if(emailText.length() < 8) loginPassword.setError("Password Incorrecto");
        else Volley.newRequestQueue(this).add(login);

    }

    public void showSnackbar(String stringSnackbar){
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
