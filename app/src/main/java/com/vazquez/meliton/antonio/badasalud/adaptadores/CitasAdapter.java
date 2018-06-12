package com.vazquez.meliton.antonio.badasalud.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vazquez.meliton.antonio.badasalud.R;
import com.vazquez.meliton.antonio.badasalud.controladores.CitaController;
import com.vazquez.meliton.antonio.badasalud.entidad.Cita;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.ViewHolder> {
    List<Cita> listaCitas;
    Context context;
    View view;
    Snackbar snackbar;
    int citaId;


    String titulo, hospital, especialidad, fecha, hora;

    public CitasAdapter(ArrayList<Cita> listaCitas, Context context) {
        this.listaCitas = listaCitas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cita_personalizado, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        citaId = listaCitas.get(position).getId();
        holder.tituloCita.setText(listaCitas.get(position).getTitulo());
        holder.hospitalCita.setText(String.valueOf(listaCitas.get(position).getHospital_id()));
        holder.especialidad.setText(String.valueOf(listaCitas.get(position).getEspecialidad_id()));
        holder.fecha.setText(listaCitas.get(position).getFecha());
        holder.hora.setText(listaCitas.get(position).getHora());

        holder.imagen.setImageResource(R.drawable.iconcita);

        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.option_menu_citas);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.eliminarCita:
                                eliminarCita(position);
                                break;

                            case R.id.agregarAlarma:
                                //recogemos datos de la cita
                                titulo = listaCitas.get(position).getTitulo();
                                hospital = String.valueOf(listaCitas.get(position).getHospital_id());
                                especialidad = String.valueOf(listaCitas.get(position).getEspecialidad_id());
                                fecha = listaCitas.get(position).getFecha();
                                hora = listaCitas.get(position).getHora();

                                //enviamos datos al calendario en modo evento desde un intent implícito
                                Calendar cal = Calendar.getInstance();
                                Intent intent = new Intent(Intent.ACTION_EDIT);
                                intent.setType("vnd.android.cursor.item/event");
                                intent.putExtra("beginTime",hora);
                                intent.putExtra("beginDate",fecha);
                                intent.putExtra("description", hospital + "\n" + especialidad);
                                intent.putExtra("allDay", true);
                                intent.putExtra("rrule", "FREQ=YEARLY");
                                intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
                                intent.putExtra("title", titulo);
                                context.startActivity(intent);
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });
    }

    private void eliminarCita(int position) {
        //llamamos al controlador para eliminar la cita de la base de datos
        CitaController citaController = new CitaController(context,view);

        citaController.eliminarCita(citaId);
        // elimina de la lista al pulsar en el botón (es solo válido para la vista)
        listaCitas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listaCitas.size());
        //mostramos snackbar con el éxito
        Snackbar.make(view, "Cita eliminada con éxito", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
}


    @Override
    public int getItemCount() {
        return listaCitas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tituloCita, hospitalCita, especialidad, fecha, hora, buttonViewOption;
        ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            tituloCita = itemView.findViewById(R.id.tituloCita);
            hospitalCita = itemView.findViewById(R.id.hospitalCita);
            especialidad = itemView.findViewById(R.id.especialidadCita);
            fecha = itemView.findViewById(R.id.fechaCita);
            hora = itemView.findViewById(R.id.horaCita);

            imagen=itemView.findViewById(R.id.imagenCita);
            buttonViewOption = itemView.findViewById(R.id.textViewOptions);
        }
    }


}
