package com.vazquez.meliton.antonio.badasalud.controladores;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.vazquez.meliton.antonio.badasalud.constantes.Constantes;
import com.vazquez.meliton.antonio.badasalud.constantes.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

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
    public void nuevaCita(String titulo, int usuarioid, int hospitalid, int especialidadid, String fecha, String hora) {
        //inicio mapeo de guardado
        final HashMap<String, Object> map = new HashMap<>();
        map.put("titulo", titulo);
        map.put("usuario_id", usuarioid);
        map.put("hospital_id", hospitalid);
        map.put("especialidad_id", especialidadid);
        map.put("fecha", fecha);
        map.put("hora", hora);


        // Creamos un objeto dandole los datos del mapeo
        final JSONObject jsonObject = new JSONObject(map);

        //lanzamos volley para procesar su insercción
        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(
                new JsonObjectRequest(Request.Method.POST, Constantes.INSERT_CITA, jsonObject,
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
                                //ConsoleMessage.MessageLevel.valueOf("Error: " + error.getMessage());
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
    public void eliminarCita(String id) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id",id);
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
