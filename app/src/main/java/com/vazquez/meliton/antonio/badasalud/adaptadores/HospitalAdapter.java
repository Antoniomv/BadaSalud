package com.vazquez.meliton.antonio.badasalud.adaptadores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vazquez.meliton.antonio.badasalud.R;
import com.vazquez.meliton.antonio.badasalud.entidad.Hospital;

import java.util.List;

public class HospitalAdapter  extends RecyclerView.Adapter<HospitalAdapter.HospitalHolder>{
    //declaramos lista
    List<Hospital> listaHospitales;

    //creamos constructor
    public HospitalAdapter(List<Hospital> listaHospitales){
        this.listaHospitales = listaHospitales;
    }

    @NonNull
    @Override
    public HospitalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_personalizado, parent,false );
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new HospitalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalHolder holder, int position) {
    holder.titulo.setText(listaHospitales.get(position).getNombre().toString());
    holder.direccion.setText(listaHospitales.get(position).getDireccion().toString());
    holder.telefono.setText(listaHospitales.get(position).getTelefono());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HospitalHolder extends RecyclerView.ViewHolder {
        TextView titulo,direccion,telefono;
        ImageView imagen;

        public HospitalHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.nombreHospital);
            direccion = itemView.findViewById(R.id.direccionHospital);
            telefono = itemView.findViewById(R.id.telefonoHospital);
            imagen = itemView.findViewById(R.id.imagenHospital);

        }
    }
}
