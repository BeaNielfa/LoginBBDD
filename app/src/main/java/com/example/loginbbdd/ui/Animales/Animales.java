package com.example.loginbbdd.ui.Animales;





public class Animales
{
    public static final String ANIMAL_ID = "id";
    public static final String ANIMAL_NOMBRE = "nombre";
    public static final String ANIMAL_RAZA = "raza";
    public static final String ANIMAL_COLOR = "color";



    private int id;
    private String nombre;
    private String color;
    private String urlFoto;
    private String raza;

    public Animales() {

    }

    public Animales(String nombre, String color,  String raza, int id) {
        this.nombre = nombre;
        this.color = color;
        // this.urlFoto = urlFoto;
        this.raza = raza;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}
