package com.vazquez.meliton.antonio.badasalud.Controladores;

import android.content.Context;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.vazquez.meliton.antonio.badasalud.Constantes.Constantes;
import com.vazquez.meliton.antonio.badasalud.Constantes.VolleySingleton;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class UsuarioController {

    //nos traemos el contexto y la vista
    private Context context;
    private View view;


    //inicializamos constructor
    public UsuarioController(Context context, View view) {
        this.context = context;
        this.view = view;
    }


    //Mapeamos para traernos los datos del formulario y poder crear usuario y guardarlo
    public void nuevoUsuario(String nombre, String apellidos, String telefono, String email, String password) {
        //inicio mapeo de guardado
        final HashMap<String, Object> map = new HashMap<>();
        map.put("nombre", nombre);
        map.put("apellidos", apellidos);
        map.put("telefono", telefono);
        map.put("email", email);
        map.put("password", password);

        // Creamos un objeto dandole los datos del mapeo
        final JSONObject jsonObject = new JSONObject(map);

        //lanzamos volley para procesar su insercción
        VolleySingleton.getInstance(context).addToRequestQueue(
                new JsonObjectRequest(Request.Method.POST, Constantes.INSERT_USUARIO, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    procesadoCreacion(response); // Procesar la respuesta Json
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(context, "Guardado con Éxito", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                ConsoleMessage.MessageLevel.valueOf("Error: " + error.getMessage());
                                Toast.makeText(context, map.toString(), Toast.LENGTH_SHORT).show();
                            }
                        })
        );
    }

    //Procesado realizado por JSON
    public void procesadoCreacion(JSONObject response) throws JSONException {
        // Obtener valor de estado
        String estado = response.getString("estado");
        switch (estado) {
            case "1": // Ok
                Toast.makeText(context, "usuario agregado correctamente", Toast.LENGTH_LONG).show();
                trasladoLogin();
                break;
            case "2": // error
                Toast.makeText(context, response.getString("mensaje"), Toast.LENGTH_LONG).show();
                break;
        }

    }

    //método para pasar al fragmento login una vez esté registrado con éxito
    private void trasladoLogin() {
//        FragmentManager fm = get;
//        FragmentTransaction ft = fm.beginTransaction();
//
//        LoginFragment loginFragment = new LoginFragment();
//        ft.replace(R.id.fragment_container, loginFragment);
//        ft.addToBackStack(null);
//        ft.commit();
    }


    //Mapeamos, actualizamos datos, procesamos y guardamos
    public void ActualizarUsuario(String nombre, String apellidos, String telefono, String email, String password) {
        //inicio mapeo de guardado
        final HashMap<String, String> map = new HashMap<>();
        map.put("nombre", nombre);
        map.put("apellidos", apellidos);
        map.put("telefono", telefono);
        map.put("email", email);
        map.put("password", password);

        // Creamos un objeto dandole los datos del mapeo
        final JSONObject jsonObject = new JSONObject(map);

        //lanzamos volley para procesar su insercción
        VolleySingleton.getInstance(context).addToRequestQueue(
                new JsonObjectRequest(Request.Method.POST, Constantes.UPDATE_USUARIO, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    procesadoActualizacion(response); // Procesar la respuesta Json
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(context, "Guardado con Éxito", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                ConsoleMessage.MessageLevel.valueOf("Error: " + error.getMessage());
                                Toast.makeText(context, map.toString(), Toast.LENGTH_SHORT).show();

                            }
                        })
        );
    }

    //Procesado realizado por JSON
    public void procesadoActualizacion(JSONObject response) throws JSONException {
        // Obtener valor de estado
        String estado = response.getString("estado");
        switch (estado) {
            case "1": // Ok
                Toast.makeText(context, "usuario actualizado correctamente", Toast.LENGTH_LONG).show();
                break;
            case "2": // error
                Toast.makeText(context, response.getString("mensaje"), Toast.LENGTH_LONG).show();
                break;
        }

    }
}
