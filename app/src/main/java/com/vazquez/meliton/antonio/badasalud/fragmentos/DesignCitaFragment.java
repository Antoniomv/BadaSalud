package com.vazquez.meliton.antonio.badasalud.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.vazquez.meliton.antonio.badasalud.R;
import com.vazquez.meliton.antonio.badasalud.constantes.VolleySingleton;
import com.vazquez.meliton.antonio.badasalud.entidad.Especialidad;
import com.vazquez.meliton.antonio.badasalud.entidad.Hospital;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DesignCitaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DesignCitaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DesignCitaFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //datos para hospitales
    ArrayList<Hospital> listaHospitales;
    JsonObjectRequest jsonObjectRequestHospitales;
    private View view;
    private Context context;

    ArrayList<Especialidad> listaEspecialidades;
    JsonObjectRequest jsonObjectRequestEspecialidades;

    private OnFragmentInteractionListener mListener;


    private String selectionHospital;
    private String selectionEspecialidad;


    public static final int DEFAULT_POSITION = 3;

    public DesignCitaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DesignCitaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DesignCitaFragment newInstance(String param1, String param2) {
        DesignCitaFragment fragment = new DesignCitaFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_design_cita, container, false);
        listaHospitales = new ArrayList<>();
        listaEspecialidades = new ArrayList<>();

        webServiceHospitales();
        spinnerHospitales();

        webServiceEspecialidades();
        spinnerEspecialidades();

        return view;
    }



    private void spinnerHospitales() {
        List<String> hospitales = new ArrayList<String>();

        for (int i = 0; i < listaHospitales.size(); i++) {
            hospitales.add(listaHospitales.get(i).getNombre());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, hospitales);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerHospital = view.findViewById(R.id.spinner_hospital);

        spinnerHospital.setAdapter(spinnerAdapter);



        spinnerHospital.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            /*
            Obtienes el item actualmente seleccionado
             */
                selectionHospital = parent.getItemAtPosition(position).toString();

                /*
                Mostramos la selección actual del Spinner
                 */
                Toast.makeText(getContext(),"Selección actual: "+selectionHospital,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    private void spinnerEspecialidades() {

        List<String> especialidades = new ArrayList<String>();

        for (int i = 0; i < listaEspecialidades.size(); i++) {
            especialidades.add(listaEspecialidades.get(i).getEspecialidad(selectionEspecialidad));
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, especialidades);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerEspecialidad = view.findViewById(R.id.spinner_especialidad);

        spinnerEspecialidad.setAdapter(spinnerAdapter);



        spinnerEspecialidad.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            /*
            Obtienes el item actualmente seleccionado
             */
                selectionEspecialidad = parent.getItemAtPosition(position).toString();

                /*
                Mostramos la selección actual del Spinner
                 */
                Toast.makeText(getContext(),"Selección actual: "+selectionHospital,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void webServiceHospitales() {
        String URL="http://badasalud.es/webservice/hospitales/get_hospital.php";
        jsonObjectRequestHospitales=new JsonObjectRequest(Request.Method.GET,URL,null,this,this);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequestHospitales);
    }

    private void webServiceEspecialidades(){
        String URL="http://badasalud.es/webservice/especialidades/get_especialidad.php";
        jsonObjectRequestEspecialidades=new JsonObjectRequest(Request.Method.GET,URL,null,this,this);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequestHospitales);
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    public void onResponseNew(JSONObject responsenew) {
        respuestaHospital(responsenew);

    }
    @Override
    public void onResponse(JSONObject response) {
        respuestaEspecialidad(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    private void respuestaEspecialidad(JSONObject response) {
        Especialidad especialidad = null;

        JSONArray json=response.optJSONArray("especialidades");

        try{
            for (int i=0;i<json.length();i++){
                especialidad=new Especialidad();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                especialidad.getEspecialidad(jsonObject.optString("NOMBRE"));
                listaEspecialidades.add(especialidad);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
        }
    }



    private void respuestaHospital(JSONObject responseNew) {
        Hospital hospital = null;

        JSONArray json=responseNew.optJSONArray("hospitales");

        try{
            for (int i=0;i<json.length();i++){
                hospital=new Hospital();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                hospital.setNombre(jsonObject.optString("NOMBRE"));
                listaHospitales.add(hospital);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+responseNew, Toast.LENGTH_LONG).show();
        }
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
