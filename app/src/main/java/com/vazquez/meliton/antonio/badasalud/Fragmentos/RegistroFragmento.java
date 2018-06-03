package com.vazquez.meliton.antonio.badasalud.Fragmentos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vazquez.meliton.antonio.badasalud.Controladores.UsuarioController;
import com.vazquez.meliton.antonio.badasalud.Principal;
import com.vazquez.meliton.antonio.badasalud.R;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistroFragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistroFragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragmento extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //referencio
    EditText nombre, apellidos, telefono, email, password;
    Button registro;
    TextView login, advertencia;
    private UsuarioController usuarioController;
    //ventana de progreso en caso de que tarde en cargar
    ProgressDialog progress;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;


    public RegistroFragmento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroFragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroFragmento newInstance(String param1, String param2) {
        RegistroFragmento fragment = new RegistroFragmento();
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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registro, container, false);

        //Doy valor a las variables
        nombre = (EditText) view.findViewById(R.id.et_nombre);
        apellidos = (EditText) view.findViewById(R.id.et_apellidos);
        telefono = (EditText) view.findViewById(R.id.et_telefono);
        email = (EditText) view.findViewById(R.id.et_email);
        password = (EditText) view.findViewById(R.id.et_password);

        advertencia = (TextView) view.findViewById(R.id.advertencia);
        registro = (Button) view.findViewById(R.id.registro);
        login = (TextView) view.findViewById(R.id.login);


        //creamos método para el botón
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insertamos usuario
                nuevoUsuario(view);

                //Limpiamos el formulario
                limpiarRegistro();
            }
        });

        //creamos método para ir al login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                LoginFragment loginFragment = new LoginFragment();
                ft.replace(R.id.fragment_container, loginFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        return view;
    }

    //método para añadir nuevo usuario
    private void nuevoUsuario(View view) {


        //importamos el controllador
        usuarioController = new UsuarioController(getContext(), view);
        //insertamos valores y los transformamos en String
        String nombreGuardar = nombre.getText().toString();
        String apellidosGuardar = apellidos.getText().toString();
        String telefonoGuardar = telefono.getText().toString();
        String emailGuardar = email.getText().toString();
        String passwordGuardar = password.getText().toString();

        //Evitamos que se manden datos vacíos
        Boolean ok = true;
        Boolean entrarLogin = false;
        if ((nombreGuardar.isEmpty()) || (apellidosGuardar.isEmpty()) || (telefonoGuardar.isEmpty()) || (emailGuardar.isEmpty()) || (passwordGuardar.isEmpty())) {

            if (nombreGuardar.isEmpty()) {
                nombre.setError("El nombre no puede estar vacío");
            }
            if (apellidosGuardar.isEmpty()) {
                apellidos.setError("El apellidos no puede estar vacío");
            }
            if (telefonoGuardar.isEmpty()) {
                telefono.setError("El telefono no puede estar vacío");
            }
            if (emailGuardar.isEmpty()) {
                email.setError("El email no puede estar vacío");
            }
            if (passwordGuardar.isEmpty()) {
                password.setError("El password no puede estar vacío");
            }
            ok = false;
        }

        if (ok) {
            usuarioController.nuevoUsuario(nombreGuardar, apellidosGuardar, telefonoGuardar, emailGuardar, passwordGuardar);
            entrarLogin = true;
        }
        if (entrarLogin) {
            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getContext(), Principal.class);
                    startActivity(intent);
                }
            };
            handler.postDelayed(runnable, 2000);
        }
    }

    //método para limpiar registro
    private void limpiarRegistro() {
        nombre.setText("");
        apellidos.setText("");
        telefono.setText("");
        email.setText("");
        password.setText("");
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
