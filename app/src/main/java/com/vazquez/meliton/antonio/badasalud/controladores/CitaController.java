package com.vazquez.meliton.antonio.badasalud.controladores;

import android.content.Context;
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

        Cita id = data.remove(pos);

        //lanzamos volley para procesar su insercción
        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(
                new JsonObjectRequest(Request.Method.DELETE, Constantes.DELETE_CITA, id,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    procesadoBorrado(response); // Procesar la respuesta Json
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(context, "Eliminado con Éxito", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                ConsoleMessage.MessageLevel.valueOf("Error: " + error.getMessage());
                                Toast.makeText(context, "eliminado mal", Toast.LENGTH_SHORT).show();

                            }
                        })
        );
    }

    //Procesado realizado por JSON
    public void procesadoBorrado(JSONObject response) throws JSONException {
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
