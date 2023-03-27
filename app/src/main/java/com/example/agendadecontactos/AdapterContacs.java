package com.example.agendadecontactos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendadecontactos.db.ModeloContactos;

import java.util.List;

public class AdapterContacs extends RecyclerView.Adapter<AdapterContacs.ViewHolder> {


    TextView txtLetraInicial;
    private List<ModeloContactos> list;

    public AdapterContacs(List<ModeloContactos> list) {
        this.list = list;
    }
    public void setContactos(List<ModeloContactos> contactos) {
        list = contactos;
    }

    @NonNull
    @Override
    public AdapterContacs.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContacs.ViewHolder holder, int position) {
        holder.setContacto(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLetraInicial = itemView.findViewById(R.id.txtInicial);
            textViewNombre = itemView.findViewById(R.id.txtNombre);
        }
        public void setContacto(ModeloContactos modeloContactos){
            textViewNombre.setText(modeloContactos.getNombre());
            String inicial = String.valueOf(modeloContactos.getNombre().charAt(0));
            //char inicialMayus= Character.toUpperCase(inicial);
            txtLetraInicial.setText(inicial);
        }
    }

}
