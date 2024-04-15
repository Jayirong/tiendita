package com.tienda.tiendita.Model;

public class Producto {
    private int id;
    private String nombre;
    private int precio;
    
    public Producto(int id, String nombre, int precio){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getIdProd(){
        return id;
    }

    public String getNombreProd(){
        return nombre;
    }

    public int getPrecioProd(){
        return precio;
    }
}
