package com.vazquez.meliton.antonio.badasalud.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.vazquez.meliton.antonio.badasalud.Controladores.UsuarioController;
import com.vazquez.meliton.antonio.badasalud.Entidad.Usuario;
import com.vazquez.meliton.antonio.badasalud.Principal;
import com.vazquez.meliton.antonio.badasalud.R;

import org.json.JSONObject;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Declaramos valores
    EditText loginEmail,loginPassword;
    Button entrarLogin;
    JSONObject jsonObject=null;
    private UsuarioController usuarioController;


    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        //Damos valor a las variables
        loginEmail = view.findViewById(R.id.et_loginEmail);
        loginPassword = view.findViewById(R.id.et_LoginPassword);
        entrarLogin = view.findViewById(R.id.entrarLogin);

        //ejecutamos botón
        entrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        return view;
    }

    //método para iniciar sesión
    public void iniciarSesion() {
        final String email = loginEmail.getText().toString();
        final String password = loginPassword.getText().toString();

    //importamos el controllador
        usuarioController = new UsuarioController(getContext(),getView());
        //insertamos valores y los transformamos en String
        String emailGuardar = loginEmail.getText().toString();
        String passwordGuardar = loginPassword.getText().toString();

        List<Usuario> listaTemporal= usuarioController.getUsuarios();
        
        //Evitamos que se manden datos vacíos
        Boolean ok = true;
        Boolean entrarLogin = false;
        if ((emailGuardar.isEmpty()) || (passwordGuardar.isEmpty())) {

            if (emailGuardar.isEmpty()) {
                loginEmail.setError("El email no puede estar vacío");
            }
            if (passwordGuardar.isEmpty()) {
                loginPassword.setError("El password no puede estar vacío");
            }
            ok = false;
        }

        //si la comprobación es correcta, comparamos los datos con la Base de datos
        if (ok) {
            usuarioController.trasladoLogin(emailGuardar, passwordGuardar);
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
            handler.postDelayed(runnable, 1500);
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
