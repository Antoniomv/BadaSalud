package com.vazquez.meliton.antonio.badasalud.controladores;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.vazquez.meliton.antonio.badasalud.R;
import com.vazquez.meliton.antonio.badasalud.adaptadores.HospitalAdapter;
import com.vazquez.meliton.antonio.badasalud.constantes.Constantes;
import com.vazquez.meliton.antonio.badasalud.constantes.VolleySingleton;
import com.vazquez.meliton.antonio.badasalud.entidad.Hospital;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class HospitalController {
    private static final String TAG = "MensajesWeb";
    private Context context; //Contexto desde donde se está realizando la acción
    private View view; //Vista donde se está ejecutando
    private Gson gson = new Gson();

    public HospitalController(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public void getHospitales() {
        VolleySingleton.getInstance(context).addToRequestQueue(
                new JsonObjectRequest(Request.Method.GET, Constantes.GET_HOSPITALES, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                procesarRespuesta(response); // Procesar la respuesta Json
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "Error Volley: " + error.getMessage());
                            }
                        }

                )
        );
    }

    private void procesarRespuesta(JSONObject response) {
        try {
            Log.d(TAG, "Respuesta: " + response.toString());
            String estado = response.getString("estado");

            switch (estado) {
                case "1": // correcto
                    JSONArray mensaje = response.getJSONArray("hospitales");  //APN: Este era otro fallo, tu traías mensajes, no hospitales
                    Hospital[] hospitales = gson.fromJson(mensaje.toString(), Hospital[].class); // preparar con Gson
                    // Inicializar adaptador
                    HospitalAdapter<Hospital> adapter = new HospitalAdapter<Hospital>(view.getContext(),(ArrayList<Hospital>) new ArrayList<>(Arrays.asList(hospitales)));
                    ListView listViewMensaje = view.findViewById(R.id.rv_hospital);
                    listViewMensaje.setAdapter(adapter);
                    break;
                case "2": // error
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            context,
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
