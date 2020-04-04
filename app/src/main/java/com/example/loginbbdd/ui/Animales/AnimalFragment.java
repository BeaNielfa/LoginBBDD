package com.example.loginbbdd.ui.Animales;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginbbdd.OnAnimalesInteractionListener;
import com.example.loginbbdd.R;

import java.io.File;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class AnimalFragment extends Fragment {

    OnAnimalesInteractionListener mListener;
    ArrayList<Animales> animalesList = new ArrayList<>();;

    public AnimalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animal_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            insercionDatosPrueba();
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            consultarDatos();
           /* animalesList.add(new Animales("Aquiles","Marrón","https://misanimales.com/wp-content/uploads/2019/10/perros-arrugas.jpg","Shar pei"));
            animalesList.add(new Animales("Dana", "Negro","https://d2devwt40at1e2.cloudfront.net/api/file/wSXTJACZT2i7mTZoESYA/convert?width=600&fit=max&quality=80","Salchica"));
            animalesList.add(new Animales("Titan","Marrón","https://www.hola.com/imagenes/estar-bien/20191004150785/pastor-aleman-raza-de-perro-caracteristicas/0-728-57/raza-de-perro-pastor-aleman-m.jpg","Pastor Aleman"));
            animalesList.add(new Animales("Layka","Blanco y Negro" ,"https://www.petclic.es/wikipets/wp-content/uploads/sites/default/files/library/dalmata_-_razas_de_perro.jpg","Dalmata"));
*/

            recyclerView.setAdapter(new MyAnimalRecyclerViewAdapter(getActivity(),animalesList, mListener));
        }
        return view;
    }

    /**
     * Nos traemos los datos de la BD con una consulta y construimos la lista.
     * Primero limpiamos la lista para que no haya repetidos.
     */
    private void consultarDatos() {
        animalesList.clear();
        ControladorBD bdAnimales = new ControladorBD(getContext(), "BDAnimales", null, 1);
        SQLiteDatabase bd = bdAnimales.getReadableDatabase();
        Animales animal = null;
        if (bd != null) {
            Cursor c = bd.rawQuery("SELECT nombre, color, raza, id FROM Animales ", null);

            if (c.moveToFirst()) {
                do {

                    animal = new Animales();
                    animal.setNombre(c.getString(0));
                    animal.setColor(c.getString(1));
                    animal.setRaza(c.getString(2));
                    animal.setId(c.getInt(3));
                    animalesList.add(animal);



                } while (c.moveToNext());
            }
            bd.close();
            bdAnimales.close();
        }

    }

    private void insercionDatosPrueba() {
        File fichero = new File("//data/data/com.example.loginbbdd/databases/BDAnimales");
        if (!fichero.exists()) {
            ControladorBD bdJuegos = new ControladorBD(getContext(), "BDAnimales", null, 1);
            SQLiteDatabase bd = bdJuegos.getWritableDatabase();
            bd.execSQL("INSERT INTO Animales (nombre, color, raza) VALUES ('Layka','Blanco y negro', 'Dalmata')");
            bd.close();
            bdJuegos.close();

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAnimalesInteractionListener) {
            mListener = (OnAnimalesInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
