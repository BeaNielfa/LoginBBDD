package com.example.loginbbdd;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.loginbbdd.ui.Animales.Animales;
import com.example.loginbbdd.ui.Animales.ControladorBD;
import com.example.loginbbdd.ui.Animales.NuevAveriaDialogo;
import com.example.loginbbdd.ui.Animales.ScrollingActivityDetalleAnimal;
import com.example.loginbbdd.ui.Animales.edit_animal_dialog_fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;

public class MainActivity  extends AppCompatActivity implements OnAnimalesInteractionListener , OnNuevaAveriaListener {

    private AppBarConfiguration mAppBarConfiguration;
    DialogFragment dialogoNuevaAnimal, dialogoEditarAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoNuevaAnimal = new NuevAveriaDialogo();
                dialogoNuevaAnimal.show(getSupportFragmentManager(),"NuevaAveríaDialogo");
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //Personalizacion del contenido del navigationView header
        ImageView ivAvatar = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageViewAvatar);

        ivAvatar.setImageResource(R.drawable.ic_ajustes);
        TextView nombre = (TextView)  navigationView.getHeaderView(0).findViewById(R.id.textViewNombre);
        TextView  email = (TextView)  navigationView.getHeaderView(0).findViewById(R.id.textViewEmail);

        nombre.setText("Beatriz Nielfa");
        email.setText("beanielfa1396@gmail.com");



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_animal, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onAnimalClick(Animales animal) {
        Intent i = new Intent(this, ScrollingActivityDetalleAnimal.class);
        i.putExtra(Animales.ANIMAL_ID, animal.getId());
        startActivity(i);
    }

    @Override
    public void onAnimalEdit(Animales mItem) {
        dialogoEditarAnimal = edit_animal_dialog_fragment.newInstance(mItem.getId(), mItem.getNombre(), mItem.getColor(), mItem.getRaza());
        dialogoEditarAnimal.show(getSupportFragmentManager(),"EditAveriaDialogo");
    }

    @Override
    public void onAnimalBorrar(Animales mItem) {
        mostrarDialogoEliminar(mItem);
    }

    private void mostrarDialogoEliminar(final Animales mItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Animal");
        builder.setMessage("¿Desea eliminar definitivamente el animal "+mItem.getNombre()+"?");
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {
                borrarAnimal(mItem.getId());
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void borrarAnimal(int id) {
        try {
            ControladorBD bdAnimales = new ControladorBD(this, "BDAnimales", null, 1);
            SQLiteDatabase bd = bdAnimales.getWritableDatabase();
            bd.execSQL("DELETE FROM Animales WHERE id = " + id);
            bd.close();
            bdAnimales.close();

        } catch (Exception e) {

        }
    }

    @Override
    public void onAveriaGuardarClickListener(final String nombre, final String color, final String raza) {
        //Abrimos la conexión a la BD
        //try {
        ControladorBD bdAnimales = new ControladorBD(this, "BDAnimales", null, 1);
        SQLiteDatabase bd = bdAnimales.getWritableDatabase();
        bd.execSQL("INSERT INTO Animales (nombre,color,raza) VALUES " +
                "('" + nombre + "','" + color + "','" + raza + "')");
        bd.close();
        bdAnimales.close();
        Toast.makeText(this, "Animal guardado", Toast.LENGTH_SHORT).show();

        //} catch (Exception e) {
        //  Toast.makeText(this, "Estoy en el catch", Toast.LENGTH_SHORT).show();
        //}
    }

    @Override
    public void onAveriaActualizarClickListener(final long id,final String nombre,final String color,final String raza) {

        ControladorBD bdAnimales = new ControladorBD(this, "BDAnimales", null, 1);
        SQLiteDatabase bd = bdAnimales.getWritableDatabase();
        bd.execSQL("UPDATE Animales SET nombre = '" + nombre.trim() + "', color = '" + color.trim() + "', raza= '" + raza.trim()
                + "' WHERE id = " + id);
        bd.close();
        Toast.makeText(this, id+" este es el id", Toast.LENGTH_SHORT).show();
        bdAnimales.close();
        Toast.makeText(this, "AVERIA ACTUALIZADA", Toast.LENGTH_SHORT).show();
    }
}
