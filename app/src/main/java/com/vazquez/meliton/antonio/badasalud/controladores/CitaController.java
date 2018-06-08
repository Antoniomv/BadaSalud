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
    private ArrayList<Cita> data;


    //inicializamos constructor
    public CitaController(Context context,ArrayList<Cita> data) {
        this.context = context;
        this.view = view;
        this.data = data;
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
                                    //procesarRespuesta(response); // Procesar la respuesta Json
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
