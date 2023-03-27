package com.example.agendadecontactos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.agendadecontactos.db.Database;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    contactFragment mFirstFragment = new contactFragment();
    favoritesFragment mSecondFragment = new favoritesFragment();
    teclado teclado = new teclado();

    //Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnCrear = findViewById(R.id.button2);
//
//        btnCrear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Database dBayudante = new Database(MainActivity.this);
//                SQLiteDatabase db = dBayudante.getWritableDatabase();
//                if (db != null) {
//                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
//                }else {
//                    Toast.makeText(MainActivity.this, "ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(mSecondFragment);

        FloatingActionButton fab = findViewById(R.id.fabTeclado);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(teclado);
                fab.setVisibility(View.GONE);
            }
        });
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FloatingActionButton fab = findViewById(R.id.fabTeclado);
            switch (item.getItemId()){
                case R.id.fragmentoContacto:
                    loadFragment(mFirstFragment);
                    fab.setVisibility(View.VISIBLE);
                    return true;
                case R.id.fragmentoFavoritos:
                    loadFragment(mSecondFragment);
                    fab.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}