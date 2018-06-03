package com.vazquez.meliton.antonio.badasalud.Controladores;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginController extends StringRequest{

    private static final String LOGIN_REQUEST_URI="http://badasalud.es/webservice/usuarios/get_usuario_login.php";
    private Map<String, String> params;
    public LoginController(String email, String password, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URI, listener, null);
        params=new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
