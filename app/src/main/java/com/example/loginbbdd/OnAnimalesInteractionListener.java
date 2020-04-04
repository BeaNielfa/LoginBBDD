package com.example.loginbbdd;


import com.example.loginbbdd.ui.Animales.Animales;

public interface OnAnimalesInteractionListener {
    public void onAnimalClick(Animales animal);

    public void onAnimalEdit(Animales mItem);

    public void onAnimalBorrar(Animales mItem);
}

