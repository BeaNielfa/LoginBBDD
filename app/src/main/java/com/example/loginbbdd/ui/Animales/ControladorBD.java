package com.example.loginbbdd.ui.Animales;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class ControladorBD extends SQLiteOpenHelper {

    //Sentencia que creerá la tabla
    private final static String CREATE_TABLA_ANIMALES = "CREATE TABLE Animales (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, " +
            "color TEXT, raza TEXT)";



    public ControladorBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Cuando se llame al onCreate se realiza la sentencia

        db.execSQL(CREATE_TABLA_ANIMALES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se borra y se vuelve a crear para "actualizar" la versión de la tabla
        db.execSQL("DROP TABLE IF EXISTS Animales");
        //Se crea la nueva versión de la tabla
        onCreate(db);
    }

}
