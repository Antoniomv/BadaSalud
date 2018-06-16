package com.vazquez.meliton.antonio.badasalud.controladores;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.vazquez.meliton.antonio.badasalud.constantes.Constantes;

import java.util.HashMap;
import java.util.Map;

public class ActualizarController extends StringRequest {
    private static final String url = Constantes.UPDATE_USUARIO;
    private Map<String, String> params;

    public ActualizarController(String id, String nombre, String apellidos, String telefono, Response.Listener<String> listener) {
        super(Method.POST, url, listener, null);
        params = new HashMap<>();
        params.put("id", id);
        params.put("nombre", nombre);
        params.put("apellidos", apellidos);
        params.put("telefono", telefono);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
