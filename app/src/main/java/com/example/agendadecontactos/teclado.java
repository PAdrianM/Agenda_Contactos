package com.example.agendadecontactos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class teclado extends Fragment {
    AgregarContacto agregar = new AgregarContacto();
    EditText txtNumerico;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public teclado() {
        // Required empty public constructor
    }

    public static teclado newInstance(String param1, String param2) {
        teclado fragment = new teclado();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teclado, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnguardar = getView().findViewById(R.id.btnAgregarContacto);
        txtNumerico = view.findViewById(R.id.txtNumeroTelefono);


        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Paso 5: Crear instancia del fragmento de destino y asignar valor al dato numérico.
                Bundle bundle = new Bundle();
                bundle.putString("numero", txtNumerico.getText().toString().trim());
                txtNumerico.setText("");
                getParentFragmentManager().setFragmentResult("key", bundle);

                // Paso 6: Utilizar FragmentManager para reemplazar el fragmento actual por el fragmento de destino.
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, agregar).commit();
            }
        });
    }
}