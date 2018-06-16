package com.vazquez.meliton.antonio.badasalud.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vazquez.meliton.antonio.badasalud.R;
import com.vazquez.meliton.antonio.badasalud.entidad.Cita;

import java.util.ArrayList;

public class ListaCitasAdapter extends BaseAdapter {

    private ArrayList<Cita> citas;
    private TextView tituloTx, especialidadTx, hospitalTx, fechaTx, horaTx, buttonViewOption;
    private ImageView imagenVista, del;

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
