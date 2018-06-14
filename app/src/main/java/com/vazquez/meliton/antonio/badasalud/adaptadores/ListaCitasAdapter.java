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
import android.widget.TextView;

import com.vazquez.meliton.antonio.badasalud.R;
import com.vazquez.meliton.antonio.badasalud.controladores.CitaController;
import com.vazquez.meliton.antonio.badasalud.entidad.Cita;

import java.util.ArrayList;

public class ListaCitasAdapter extends BaseAdapter {

    private ArrayList<Cita> citas;
    private Context context;
    private View view;
    private TextView tituloTx, especialidadTx, hospitalTx, fechaTx, horaTx, buttonViewOption;
    private ImageView imagenVista;
    private int citaId;

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
        buttonViewOption = vista.findViewById(R.id.textViewOptions);

        final Cita temp = citas.get(position);

        tituloTx.setText(temp.getTitulo());
        especialidadTx.setText(temp.getEspecialidad());
        hospitalTx.setText(temp.getHospital());
        fechaTx.setText(temp.getFecha());
        horaTx.setText(temp.getHora());

        buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //creating a popup menu
//                PopupMenu popup = new PopupMenu(context, buttonViewOption);
//                //inflating menu from xml resource
//                popup.inflate(R.menu.option_menu_citas);
//                //adding click listener
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()) {
//
//                            case R.id.eliminarCita:
//                                eliminarCita(position);
//                                break;
//
//                            case R.id.agregarAlarma:
//                                //recogemos datos de la cita
//                                String titulo = citas.get(position).getTitulo();
//                                String hospital = citas.get(position).getHospital();
//                                String especialidad = citas.get(position).getEspecialidad();
//                                String fecha = citas.get(position).getFecha();
//                                String hora = citas.get(position).getHora();
//
//                                //enviamos datos al calendario en modo evento desde un intent implícito
//                                Calendar cal = Calendar.getInstance();
//                                Intent intent = new Intent(Intent.ACTION_EDIT);
//                                intent.setType("vnd.android.cursor.item/event");
//                                intent.putExtra("beginTime",hora);
//                                intent.putExtra("beginDate",fecha);
//                                intent.putExtra("description", hospital + "\n" + especialidad);
//                                intent.putExtra("allDay", true);
//                                intent.putExtra("rrule", "FREQ=YEARLY");
//                                intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
//                                intent.putExtra("title", titulo);
//                                context.startActivity(intent);
//                                break;
//                        }
//                        return false;
//                    }
//                });
//                //displaying the popup
//                popup.show();

            }
        });

        return vista;
    }

    private void eliminarCita(int position) {
        //llamamos al controlador para eliminar la cita de la base de datos
        CitaController citaController = new CitaController(context,view);

        citaController.eliminarCita(citaId);
        // elimina de la lista al pulsar en el botón (es solo válido para la vista)
        citas.remove(position);
        //mostramos snackbar con el éxito
        Snackbar.make(view, "Cita eliminada con éxito", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
