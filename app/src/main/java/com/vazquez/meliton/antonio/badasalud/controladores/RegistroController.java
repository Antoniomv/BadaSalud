package com.vazquez.meliton.antonio.badasalud.controladores;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.vazquez.meliton.antonio.badasalud.constantes.Constantes;

import java.util.HashMap;
import java.util.Map;

public class RegistroController extends StringRequest {

    private static final String url = Constantes.INSERT_USUARIO;
    private Map<String, String> params;

    public RegistroController(String nombre, String apellidos, String telefono, String email, String password, Response.Listener<String> listener) {
        super(Method.POST, url, listener, null);

        params = new HashMap<>();
        params.put("nombre", nombre);
        params.put("apellidos", apellidos);
        params.put("telefono", telefono);
        params.put("email", email);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
