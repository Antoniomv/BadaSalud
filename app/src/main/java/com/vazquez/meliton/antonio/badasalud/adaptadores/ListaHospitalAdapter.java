package com.vazquez.meliton.antonio.badasalud.adaptadores;

import android.content.Context;
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

public class ListaHospitalAdapter extends RecyclerView.Adapter<ListaHospitalAdapter.ViewHolder> {

    List<Hospital> listaHospital;
    Context context;

    public ListaHospitalAdapter(List<Hospital> listaHospital, Context context){
        this.listaHospital = listaHospital;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_personalizado, parent, false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titulo.setText(listaHospital.get(position).getNombre().toString());
        holder.direccion.setText(listaHospital.get(position).getDireccion().toString());
        holder.telefono.setText(String.valueOf(listaHospital.get(position).getTelefono()));
        holder.imagen.setImageResource(R.drawable.iconohospital);

    }

    @Override
    public int getItemCount() {
        return listaHospital.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, direccion, telefono;
        ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.nombreHospital);
            direccion = itemView.findViewById(R.id.direccionHospital);
            telefono = itemView.findViewById(R.id.telefonoHospital);
            imagen=itemView.findViewById(R.id.imagenHospital);
        }
    }
}
