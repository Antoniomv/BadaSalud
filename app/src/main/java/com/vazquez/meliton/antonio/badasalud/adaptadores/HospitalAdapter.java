package com.vazquez.meliton.antonio.badasalud.adaptadores;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vazquez.meliton.antonio.badasalud.R;
import com.vazquez.meliton.antonio.badasalud.entidad.Hospital;

import java.util.ArrayList;

public class HospitalAdapter<H> extends BaseAdapter {

    private Context context; //context
    private ArrayList<Hospital> hospitales;


    public HospitalAdapter(Context context, ArrayList<Hospital> hospitales) {
        this.context = context;
        this.hospitales = hospitales;
    }

    @Override
    public int getCount() {
        return (hospitales == null) ? 0 : hospitales.size();
    }

    @Override
    public Object getItem(int i) {
        return hospitales.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        //acorto View para dar agilidad al código
        View v = convertView;

        if (v == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.hospital_personalizado, null);
        }

        //Elemento que se mostrará como actual
        Hospital hospital = (Hospital) getItem(position);

        //cargo nombre del hospital
        TextView nombreHospital = v.findViewById(R.id.nombreHospital);
        nombreHospital.setText(hospital.getNombre());
        //cargo descripción del hospital
        TextView direccion = v.findViewById(R.id.direccionHospital);
        direccion.setText(hospital.getDireccion());
        //cargo teléfono del hospital
        TextView telefono = v.findViewById(R.id.telefonoHospital);
        telefono.setText(String.valueOf(hospital.getTelefono()));

        //cargo imagen del hospital
        ImageView imagen = v.findViewById(R.id.imagenHospital);

        return v;
    }
}
