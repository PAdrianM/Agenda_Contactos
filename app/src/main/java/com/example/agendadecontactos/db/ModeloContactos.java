package com.example.agendadecontactos.db;

public class ModeloContactos {
    private String nombre;
    private String telefono;
    private String apellido;
    private String etiqueta;
    private boolean favoritos;

    public ModeloContactos() {
    }

    public ModeloContactos(String nombre, String telefono, String apellido, String etiqueta, boolean favoritos) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.apellido = apellido;
        this.etiqueta = etiqueta;
        this.favoritos = favoritos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public boolean isFavoritos() {
        return favoritos;
    }

    public void setFavoritos(boolean favoritos) {
        this.favoritos = favoritos;
    }
}
