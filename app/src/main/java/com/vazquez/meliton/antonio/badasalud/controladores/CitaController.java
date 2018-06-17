package com.vazquez.meliton.antonio.badasalud.controladores;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.vazquez.meliton.antonio.badasalud.constantes.Constantes;

import org.json.JSONException;
import org.json.JSONObject;

public class CitaController {

    //nos traemos el contexto y la vista
    private Context context;
    private Gson gson;


    //inicializamos constructor
    public CitaController(Context context, View view) {
        this.context = context;
    }

    public void nuevaCita(String titulo, int usuarioid, int hospitalid, int especialidadid, String fecha, String hora) {

        String url = Constantes.INSERT_CITA;
        url+="?titulo="+titulo+"&usuario_id="+usuarioid+"&hospital_id="+hospitalid+"&especialidad_id="+especialidadid+"&fecha="+fecha+"&hora="+hora;
        StringRequest request = new StringRequest(0, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        Toast.makeText(context,"cita agregada correctamente", Toast.LENGTH_LONG).show();
                        context.startActivity(new Intent(context, context.getClass()));
                    }else{
                        Toast.makeText(context,"mensaje", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, null);
        Volley.newRequestQueue(this.context).add(request);
    }

}
