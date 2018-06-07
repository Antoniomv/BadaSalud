package com.vazquez.meliton.antonio.badasalud.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.vazquez.meliton.antonio.badasalud.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HoraFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HoraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HoraFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Bundle bundle;
    private OnFragmentInteractionListener mListener;

    public HoraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HoraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HoraFragment newInstance(String param1, String param2) {
        HoraFragment fragment = new HoraFragment();
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
        View view = inflater.inflate(R.layout.fragment_hora, container, false);
        bundle = new Bundle();
        String tituloTemp = getArguments().getString("titulo");
        bundle.putString("tituloTemp", tituloTemp);
        String hospitalTemp= getArguments().getString("hospital");
        bundle.putString("hospitalTemp", hospitalTemp);
        String especialidadTemp = getArguments().getString("especialidad");
        bundle.putString("especialidadTemp", especialidadTemp);
        String fecha = "";
        if (getArguments()!=null) {
            fecha = getArguments().getString("fecha");
            bundle.putString("fecha",fecha);
        }
//        EliminarFragmento1();
        TextView textView = (TextView) view.findViewById(R.id.textoTiempo);
        textView.setText(fecha);
        TimePicker timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        final String finalFecha = fecha;
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String tiempo = Integer.toString(hourOfDay) + "-" +Integer.toString(minute);
                bundle.putString("tiempo", tiempo);
                Snackbar.make(view, finalFecha + tiempo
                        , Snackbar.LENGTH_LONG)
                        .show();
                volverAgregar();
            }
        });

        return view;
    }

    private void volverAgregar() {
        Fragment fragment = new DesignCitaFragment();
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.contenido,fragment).commit();
        FloatingActionButton mFloatingActionButton = getView().findViewById(R.id.fab);
        mFloatingActionButton.hide();
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
