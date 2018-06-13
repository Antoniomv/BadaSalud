package com.vazquez.meliton.antonio.badasalud.fragmentos;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vazquez.meliton.antonio.badasalud.LoginActivity;
import com.vazquez.meliton.antonio.badasalud.R;
import com.vazquez.meliton.antonio.badasalud.constantes.Constantes;
import com.vazquez.meliton.antonio.badasalud.entidad.Cita;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaCitaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaCitaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaCitaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private SharedPreferences sharedPreferences;

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


        //me traigo los datos del usuario logueado
        sharedPreferences = getContext().getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        //cargo listado
        verCitas();

        return view;
    }

    private void verCitas() {
        final ArrayList<Cita> citas = new ArrayList<>();
        final ListaCitasAdapter citaAdapter = new ListaCitasAdapter(citas);
        final ListView listView = getActivity().findViewById(R.id.lv_citas);
        listView.setAdapter(citaAdapter);

        //traemos el id desde el login
        String usuarioId = sharedPreferences.getString("idKey", null);
        //llamamos a la url
        String url = Constantes.GET_CITA_BY_ID+usuarioId;

        //hacemos el StringRequest de llamada al listado
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray.length() == 0) {
                        ((TextView) getActivity().findViewById(R.id.mensaje_vacio)).setText("No tienes citas pendientes.");
                    }
                    else{
                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String id = jsonObject.getString("id");
                            String titulo = jsonObject.getString("titulo");
                            String especialidad = jsonObject.getString("especialidad");
                            String hospital = jsonObject.getString("especialidad");
                            String fecha = jsonObject.getString("fecha");
                            String hora = jsonObject.getString("hora");
                            citas.add(new Cita(id,titulo,fecha,hora,hospital,especialidad));
                        }
                    }
                    citaAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, null);
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }

//    creamos una clase dentro para darle el Adaptador
    public class ListaCitasAdapter extends BaseAdapter {

        ArrayList<Cita> citas;

        public ListaCitasAdapter(ArrayList<Cita> citas) {
            this.citas = citas;
        }

        @Override
        public int getCount() {
            return citas.size();
        }

        @Override
        public Object getItem(int position) {
            return citas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();

            View view = inflater.inflate(R.layout.cita_personalizado, parent, false);

            TextView titulo = view.findViewById(R.id.tituloCita);
            TextView especialidad = view.findViewById(R.id.especialidadCita);
            TextView hospital = view.findViewById(R.id.hospitalCita);
            TextView fecha = view.findViewById(R.id.fechaCita);
            TextView hora = view.findViewById(R.id.horaCita);

            final Cita temp = citas.get(position);

            titulo.setText(temp.getTitulo());
            especialidad.setText(temp.getEspecialidad());
            hospital.setText(temp.getHospital());
            fecha.setText(temp.getFecha());
            hora.setText(temp.getHora());

            return view;
        }
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
