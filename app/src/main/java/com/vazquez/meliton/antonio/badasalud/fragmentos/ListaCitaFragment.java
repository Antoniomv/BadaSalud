package com.vazquez.meliton.antonio.badasalud.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.vazquez.meliton.antonio.badasalud.R;
import com.vazquez.meliton.antonio.badasalud.adaptadores.CitasAdapter;
import com.vazquez.meliton.antonio.badasalud.constantes.Constantes;
import com.vazquez.meliton.antonio.badasalud.constantes.VolleySingleton;
import com.vazquez.meliton.antonio.badasalud.entidad.Cita;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaCitaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaCitaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaCitaFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView reciclerCitas;
    ArrayList<Cita> listaCitas;
    JsonObjectRequest jsonObjectRequest;

    public ListaCitaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaCitaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaCitaFragment newInstance(String param1, String param2) {
        ListaCitaFragment fragment = new ListaCitaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_cita, container, false);

        listaCitas = new ArrayList<>();

        reciclerCitas = view.findViewById(R.id.rv_citas);
        reciclerCitas.setLayoutManager(new LinearLayoutManager(this.getContext()));
        reciclerCitas.setHasFixedSize(true);

        webService();

        return view;
    }

    private void webService() {
        String idUsuarioLogin = getArguments().getString("id");

        String URL = "http://badasalud.es/webservice/citas/get_cita.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, this, this);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
        System.out.println("ID REGOCGIDO---------------------"+idUsuarioLogin);

//        getCitas(Integer.parseInt(idUsuarioLogin));
    }

//    public void getCitas(Integer idUsuario) {
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("id", idUsuario);
//        JSONObject jsonObject = new JSONObject(map);
//
//        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(
//                new JsonObjectRequest(Request.Method.GET, Constantes.GET_CITA_BY_ID, jsonObject,
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//
//                                System.out.println("CITAS-------------------------------" + response);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                System.out.println("CITAS ERROR-------------------------------");
//
//                            }
//                        })
//        );
//    }



    @Override
    public void onResponse(JSONObject response) {
        Cita cita = null;

        JSONArray json=response.optJSONArray("citas");

        try{
            for (int i=0;i<json.length();i++){
                cita=new Cita();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                cita.setTitulo(jsonObject.optString("TITULO"));
                cita.setHospital_id(jsonObject.optInt("HOSPITAL_ID"));
                cita.setEspecialidad_id(jsonObject.getInt("ESPECIALIDAD_ID"));
                cita.setFecha(String.valueOf(jsonObject.opt("FECHA")));
                cita.setHora(String.valueOf(jsonObject.opt("HORA")));
                listaCitas.add(cita);
            }

            CitasAdapter adapter = new CitasAdapter(listaCitas, getContext());
            reciclerCitas.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InicioFragment.OnFragmentInteractionListener) {
            mListener = (ListaCitaFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
