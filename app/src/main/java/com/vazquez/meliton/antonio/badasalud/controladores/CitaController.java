package com.vazquez.meliton.antonio.badasalud.controladores;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.vazquez.meliton.antonio.badasalud.adaptadores.CitasAdapter;
import com.vazquez.meliton.antonio.badasalud.constantes.Constantes;
import com.vazquez.meliton.antonio.badasalud.constantes.VolleySingleton;
import com.vazquez.meliton.antonio.badasalud.entidad.Cita;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CitaController {

    //nos traemos el contexto y la vista
    private Context context;
    private View view;
    private Gson gson;


    //inicializamos constructor
    public CitaController(Context context, View view) {
        this.context = context;
        this.view = this.view;
    }

    //Mapeamos para traernos los datos del formulario y poder crear usuario y guardarlo
    public void nuevaCita(String titulo, String fecha, String hora, int hospitalid, int especialidadid, int usuarioid) {
        //inicio mapeo de guardado
        final HashMap<String, Object> map = new HashMap<>();
        map.put("titulo", titulo);
        map.put("fecha", fecha);
        map.put("hora", hora);
        map.put("hospitalid", hospitalid);
        map.put("especialidadid", especialidadid);
        map.put("usuarioid", usuarioid);


        // Creamos un objeto dandole los datos del mapeo
        final JSONObject jsonObject = new JSONObject(map);

        //lanzamos volley para procesar su insercción
        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(
                new JsonObjectRequest(Request.Method.POST, Constantes.INSERT_USUARIO, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    procesadoCreacion(response); // Procesar la respuesta Json
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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
                Toast.makeText(context, "cita agregada correctamente", Toast.LENGTH_LONG).show();
                break;
            case "2": // error
                Toast.makeText(context, response.getString("mensaje"), Toast.LENGTH_LONG).show();
                break;
        }

    }


    //Mapeamos, Eliminamos datos, procesamos y guardamos
    public void eliminarCita(int pos) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put("id",pos);
            JSONObject jsonObject = new JSONObject(map); // Crear nuevo objeto Json basado en el mapa

            //Volley
            VolleySingleton.getIntanciaVolley(context).addToRequestQueue(
                    new JsonObjectRequest(Request.Method.POST, Constantes.DELETE_CITA, jsonObject,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    System.out.println("Borrado Correctamente");
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    System.out.println("fallo en el borrado");
                                }
                            })  //Fin de JsonObjectRequest
            );
        }


}
