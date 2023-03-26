package com.example.agendadecontactos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agendadecontactos.db.DatabaseContactos;


public class AgregarContacto extends Fragment {
    EditText txtNumeroTelefono,txtNombre,txtApellido;

    Spinner spinnerTelefonos;

    Button btnGuardar;
    CheckBox addFavoritos;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public static AgregarContacto newInstance(String param1, String param2) {
        AgregarContacto fragment = new AgregarContacto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {

            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String name = result.getString("numero");

                txtNumeroTelefono.setText(name);
            }
        });
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_agregar_contacto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtNumeroTelefono = view.findViewById(R.id.numeroTelefono);
        txtNumeroTelefono = getView().findViewById(R.id.numeroTelefono);
        txtApellido = getView().findViewById(R.id.apellidoContacto);
        txtNombre = getView().findViewById(R.id.nombreContacto);
        spinnerTelefonos = getView().findViewById(R.id.spinnerSitios);
        btnGuardar = view.findViewById(R.id.button);
        addFavoritos = view.findViewById(R.id.chkFavoritos);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseContactos databaseContactos = new DatabaseContactos(getContext());
                long id = databaseContactos.insertaContacto(txtNombre.getText().toString(),
                        txtNumeroTelefono.getText().toString(),
                        txtApellido.getText().toString(),
                        spinnerTelefonos.getSelectedItem().toString(),
                        addFavoritos.isChecked());

                if (id > 0){
                    Toast.makeText(getContext(), "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                }else {
                    Toast.makeText(getContext(), "REGISTRO NO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });
    }
    private void limpiar(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtNumeroTelefono.setText("");
    }
}