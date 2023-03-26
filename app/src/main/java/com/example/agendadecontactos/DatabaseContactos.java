package com.example.agendadecontactos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseContactos extends MyDatabaseHelper{

    public DatabaseContactos(Context context) {
        super(context);
    }

    public List<Person> getAllPeople() {
        List<Person> peopleList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM people", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex("first_name"));
                @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex("last_name"));
                @SuppressLint("Range") String phoneNumber = cursor.getString(cursor.getColumnIndex("phone_number"));
                @SuppressLint("Range") String tag = cursor.getString(cursor.getColumnIndex("tag"));
                @SuppressLint("Range") int isFavorite = cursor.getInt(cursor.getColumnIndex("is_favorite"));

                Person person = new Person(id, firstName, lastName, phoneNumber, tag, isFavorite);
                peopleList.add(person);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return peopleList;
    }
}
