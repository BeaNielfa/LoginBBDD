package com.example.loginbbdd;

public interface OnNuevaAveriaListener {
    public void onAveriaGuardarClickListener(String nombre, String color, String raza);
    public void onAveriaActualizarClickListener(long id, String nombre, String color, String raza);
}
