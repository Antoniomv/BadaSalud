package com.vazquez.meliton.antonio.badasalud.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

    public ListaHospitalAdapter(List<Hospital> listaHospital, Context context) {
        this.listaHospital = listaHospital;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_personalizado, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        listaHospital.get(position);
        holder.titulo.setText(listaHospital.get(position).getNombre());
        holder.direccion.setText(listaHospital.get(position).getDireccion());
        holder.telefono.setText(String.valueOf(listaHospital.get(position).getTelefono()));
        holder.imagen.setImageResource(R.drawable.iconohospital);

        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.option_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_llamar:
                                llamar(position);
                                break;
                            case R.id.menu_mapa:
                                vermapa(position);
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

    private void vermapa(int position) {
        String direccion = listaHospital.get(position).getDireccion();
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.google.es/maps/place/" + direccion));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        context.startActivity(intent);
    }

    private void llamar(int position) {
        String phoneNumer = String.valueOf(listaHospital.get(position).getTelefono());
        dialPhoneNumber(phoneNumer);
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }


    @Override
    public int getItemCount() {
        return listaHospital.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, direccion, telefono, buttonViewOption;
        ImageView imagen;


        public ViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tituloCita);
            direccion = itemView.findViewById(R.id.hospitalCita);
            telefono = itemView.findViewById(R.id.especialidadCita);
            imagen = itemView.findViewById(R.id.imagenCita);

            buttonViewOption = itemView.findViewById(R.id.textViewOptions);
        }

    }
}
