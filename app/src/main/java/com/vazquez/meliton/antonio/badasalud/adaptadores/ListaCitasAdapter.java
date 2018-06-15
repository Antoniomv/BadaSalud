package com.vazquez.meliton.antonio.badasalud.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.vazquez.meliton.antonio.badasalud.R;
import com.vazquez.meliton.antonio.badasalud.controladores.CitaController;
import com.vazquez.meliton.antonio.badasalud.controladores.EliminarController;
import com.vazquez.meliton.antonio.badasalud.entidad.Cita;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaCitasAdapter extends BaseAdapter {

    private ArrayList<Cita> citas;
    private Context context;
    private View view;
    private TextView tituloTx, especialidadTx, hospitalTx, fechaTx, horaTx, buttonViewOption;
    private ImageView imagenVista, del;
    private int citaId;
    private ListView listado;

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View vista = inflater.inflate(R.layout.cita_personalizado, parent, false);

        tituloTx = vista.findViewById(R.id.tituloCita);
        especialidadTx = vista.findViewById(R.id.especialidadCita);
        hospitalTx = vista.findViewById(R.id.hospitalCita);
        fechaTx = vista.findViewById(R.id.fechaCita);
        horaTx = vista.findViewById(R.id.horaCita);

        imagenVista = vista.findViewById(R.id.imagenCita);

        final Cita temp = citas.get(position);

        tituloTx.setText(temp.getTitulo());
        especialidadTx.setText(temp.getEspecialidad());
        hospitalTx.setText(temp.getHospital());
        fechaTx.setText(temp.getFecha());
        horaTx.setText(temp.getHora());



        return vista;
    }

}
