package com.example.loginbbdd.ui.Animales;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.loginbbdd.R;

import io.realm.Realm;

public class ScrollingActivityDetalleAnimal extends AppCompatActivity {
    TextView info;
    int idAnimal;

    Animales animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_detalle_animal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        info = (TextView)findViewById(R.id.texto);

        Bundle extras  = getIntent().getExtras();
        idAnimal = extras.getInt(Animales.ANIMAL_ID);

        Animales juego = consultarDatos();

        setTitle(juego.getNombre());
        info.setText("Color: "+juego.getColor()+ "\nRaza: "+ juego.getRaza());


    }

    private Animales consultarDatos() {


        ControladorBD bdAnimales = new ControladorBD(this, "BDAnimales", null, 1);
        SQLiteDatabase bd = bdAnimales.getReadableDatabase();
        Animales animal = null;
        if (bd != null) {
            Cursor c = bd.rawQuery("SELECT nombre, color, raza, id FROM Animales WHERE id = "+idAnimal, null);

            if (c.moveToFirst()) {
                do {

                    animal = new Animales();
                    animal.setNombre(c.getString(0));
                    animal.setColor(c.getString(1));
                    animal.setRaza(c.getString(2));
                    animal.setId(c.getInt(3));

                } while (c.moveToNext());
            }
            bd.close();
            bdAnimales.close();
        }
        return animal;
    }

}
