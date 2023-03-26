package com.example.agendadecontactos.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseContactos extends Database{



    Context context;


    public DatabaseContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaContacto(String nombre, String telefono, String apellido, String etiqueta, boolean favoritos){
        long id =0;
        try {
            Database database = new Database(context);
            SQLiteDatabase db = database.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("apellido", apellido);
            values.put("etiqueta", etiqueta);
            values.put("favoritos", favoritos);

            id = db.insert(TABLE_CONTACTOS, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }
    public List<ModeloContactos> obtenerContactos(){
        List<ModeloContactos> contactoLista = new ArrayList<>();
        Database database = new Database(context);
        SQLiteDatabase base = database.getWritableDatabase();
        Cursor cursorContactos = null;
        cursorContactos = base.rawQuery("SELECT * FROM " + TABLE_CONTACTOS, null);
        ModeloContactos modeloContactos = null;

        if (cursorContactos.moveToFirst()){
            do {
                modeloContactos = new ModeloContactos();
                modeloContactos.setNombre(cursorContactos.getString(1));
                modeloContactos.setTelefono(cursorContactos.getString(2));
                modeloContactos.setApellido(cursorContactos.getString(3));
                modeloContactos.setEtiqueta(cursorContactos.getString(4));
                contactoLista.add(modeloContactos);
            }while (cursorContactos.moveToNext());

        }
        cursorContactos.close();

//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM t_contactos", null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
//                @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex("nombre"));
//                @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex("apellido"));
//                @SuppressLint("Range") String phoneNumber = cursor.getString(cursor.getColumnIndex("telefono"));
//                @SuppressLint("Range") String tag = cursor.getString(cursor.getColumnIndex("etiqueta"));
//                @SuppressLint("Range") int isFavorite = cursor.getInt(cursor.getColumnIndex("favoritos"));
//                boolean booleanFavorito = (isFavorite == 1);
//
//                ModeloContactos contacto = new ModeloContactos(firstName, phoneNumber, lastName, tag, booleanFavorito);
//                contactoLista.add(contacto);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
        return contactoLista;
    }
}
