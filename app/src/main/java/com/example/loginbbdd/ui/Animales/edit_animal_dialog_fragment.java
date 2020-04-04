package com.example.loginbbdd.ui.Animales;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.loginbbdd.OnNuevaAveriaListener;
import com.example.loginbbdd.R;


public class edit_animal_dialog_fragment extends DialogFragment {

    AlertDialog.Builder builder;
    OnNuevaAveriaListener mListener;
    View v;
    EditText etNombre, etColor, etRaza;
    Context ctx;
    private String color, nombre, raza;


    private long idAnimal;



    public edit_animal_dialog_fragment() {
        // Required empty public constructor
    }



    public static edit_animal_dialog_fragment newInstance(long id, String nombre, String color, String raza) {
        edit_animal_dialog_fragment fragment = new edit_animal_dialog_fragment();
        Bundle args = new Bundle();
        args.putLong(Animales.ANIMAL_ID, id);
        args.putString(Animales.ANIMAL_COLOR,color);
        args.putString(Animales.ANIMAL_NOMBRE,nombre);
        args.putString(Animales.ANIMAL_RAZA,raza);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idAnimal = getArguments().getLong(Animales.ANIMAL_ID);
            nombre = getArguments().getString(Animales.ANIMAL_NOMBRE);
            color = getArguments().getString(Animales.ANIMAL_COLOR);
            raza = getArguments().getString(Animales.ANIMAL_RAZA);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ctx = getActivity();

        builder =  new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        v= inflater.inflate(R.layout.dialogo_nueva_averia, null);

        //ENLAZAMS LOS VALORES
        etNombre = (EditText) v.findViewById(R.id.txtNombre);
        etColor = (EditText) v.findViewById(R.id.txtColor);
        etRaza = (EditText) v.findViewById(R.id.txtRaza);

        etNombre.setText(nombre);
        etColor.setText(color);
        etRaza.setText(raza);

        builder.setView(v);
        builder.setTitle("Editar animal")
                .setPositiveButton("Guardar",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String nombre = etNombre.getText().toString();
                        String color = etColor.getText().toString();
                        String raza = etRaza.getText().toString();

                        if(!nombre.isEmpty() && !color.isEmpty() && !raza.isEmpty()){

                            mListener.onAveriaActualizarClickListener(idAnimal,nombre, color, raza);
                            dialogInterface.dismiss();
                        }else{
                            Toast.makeText(ctx, "Introduza todos los datos", Toast.LENGTH_SHORT).show();
                        }



                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            mListener = (OnNuevaAveriaListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException((context.toString()+" must implement OnNuevaAveriaListener"));
        }
    }
}
