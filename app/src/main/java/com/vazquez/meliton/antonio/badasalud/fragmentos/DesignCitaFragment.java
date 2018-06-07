package com.vazquez.meliton.antonio.badasalud.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DesignCitaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DesignCitaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DesignCitaFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //datos para hospitales
    JsonObjectRequest jsonObjectRequestHospitales;
    private View view;
    private Context context;
    String tituloCita, hospitalSeleccionado, especialidadSeleccoinado;
    Date fechaCita;
    Time horaCita;
    Bundle bundle;
    Button agregar, retornar;
    private OnFragmentInteractionListener mListener;

    private EditText titulo_cita;
    private Spinner sp_hospital, sp_especialidad;
    private ImageView calendario;

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
        bundle = new Bundle();

        titulo_cita = view.findViewById(R.id.titulo_cita);
        tituloCita = titulo_cita.getText().toString();
        sp_hospital = view.findViewById(R.id.spinner_hospitales);
        sp_especialidad = view.findViewById(R.id.spinner_especialidades);
        hospitalSeleccionado = sp_hospital.getOnItemSelectedListener().toString();
        especialidadSeleccoinado = sp_especialidad.getOnItemSelectedListener().toString();
        bundle.putString("titulo", tituloCita);
        bundle.putString("hospital", hospitalSeleccionado);
        bundle.putString("especialidad", especialidadSeleccoinado);

        calendario = view.findViewById(R.id.calendario_cita);
        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CalendarioFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.contenido,fragment)
                        .commit();
                FloatingActionButton mFloatingActionButton = getView().findViewById(R.id.fab);
                mFloatingActionButton.hide();
            }
        });

        retornar = view.findViewById(R.id.retornar);
        retornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulo_cita.setText(getArguments().getString("tituloTemp"));
                sp_hospital.setSelected(Boolean.parseBoolean(getArguments().getString("hospitalTemp")));
                sp_especialidad.setSelected(Boolean.parseBoolean(getArguments().getString("especialidadTemp")));
                fechaCita.setTime(Long.parseLong(getArguments().getString("fecha")));
                horaCita.setTime(Long.parseLong(getArguments().getString("hora")));
            }
        });

        agregar = view.findViewById(R.id.boton_cita);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        retornoDatos();
        return view;
    }

    private void retornoDatos() {
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
