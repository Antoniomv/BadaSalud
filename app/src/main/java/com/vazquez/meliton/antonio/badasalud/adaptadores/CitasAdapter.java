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
import com.vazquez.meliton.antonio.badasalud.entidad.Cita;

import java.util.List;

public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.ViewHolder> {
    List<Cita> listaCitas;
    Context context;

    public CitasAdapter(List<Cita> listaCitas, Context context){
        this.listaCitas = listaCitas;
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
        holder.titulo.setText(listaCitas.get(position).getTitulo());
        holder.hospital.setText(String.valueOf(listaCitas.get(position).getHospital_id()));
        holder.especialidad.setText(String.valueOf(listaCitas.get(position).getEspecialidad_id()));
        holder.fecha.setText(listaCitas.get(position).getFecha().toString());
        holder.hora.setText(listaCitas.get(position).getHora().toString());

        holder.imagen.setImageResource(R.drawable.iconcita);

    }

    @Override
    public int getItemCount() {
        return listaCitas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, hospital, especialidad, fecha, hora;
        ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tituloCita);
            hospital = itemView.findViewById(R.id.hospitalCita);
            especialidad = itemView.findViewById(R.id.especialidadCita);
            fecha = itemView.findViewById(R.id.fechaCita);
            hora = itemView.findViewById(R.id.horaCita);

            imagen=itemView.findViewById(R.id.imagenCita);
        }
    }
}
