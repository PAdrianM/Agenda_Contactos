package com.example.agendadecontactos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.agendadecontactos.db.DatabaseContactos;
import com.example.agendadecontactos.db.ModeloContactos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class contactFragment extends Fragment {

    SearchView svBuscarContacto;

    AdapterContacs adapter;

    private List<ModeloContactos> listaOrdenada;

    RecyclerView recycler;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public contactFragment() {
        // Required empty public constructor
    }
    public static contactFragment newInstance(String param1, String param2) {
        contactFragment fragment = new contactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        com.example.agendadecontactos.db.DatabaseContactos databaseContactos = new DatabaseContactos(requireContext());

        svBuscarContacto = view.findViewById(R.id.buscarContacto);

        databaseContactos.obtenerContactos();
        listaOrdenada = new ArrayList<>();
        listaOrdenada = databaseContactos.obtenerContactos();
        Collections.sort(listaOrdenada, new Comparator<ModeloContactos>() {

            @Override
            public int compare(ModeloContactos c1, ModeloContactos c2) {
                return c1.getNombre().compareToIgnoreCase(c2.getNombre());
            }
        });

        recycler = view.findViewById(R.id.recyclerContactos);
        adapter = new AdapterContacs(listaOrdenada);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        svBuscarContacto.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                buscarContactos(s);
                return true;
            }
        });
        return view;
    }
    private void buscarContactos(String terminoBusqueda) {
        List<ModeloContactos> contactosEncontrados = new ArrayList<>();
        for (ModeloContactos contacto : listaOrdenada) {
            if (contacto.getNombre().toLowerCase().contains(terminoBusqueda.toLowerCase())) {
                contactosEncontrados.add(contacto);
            }
        }
        adapter = new AdapterContacs(contactosEncontrados);
        recycler.setAdapter(adapter);
    }
}