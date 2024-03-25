package com.tienda.tiendita;

import java.util.List;

public class Venta {
    private int id;
    private String fecha;
    private List<Producto> productos;
    private int totalVenta;

    public Venta(int id, String fecha, List<Producto> productos){
        this.id = id;
        this.fecha = fecha;
        this.productos = productos;
        calcularTotal();
    }

    public int getIdVenta(){
        return id;
    }

    public String getFechaVenta(){
        return fecha;
    }

    public List<Producto> getProductosVenta(){
        return productos;
    }

    //calculamos el total en base al precio de los productos en la lisa "productos"
    private void calcularTotal(){
        totalVenta = 0;
        for (Producto producto : productos){
            totalVenta += producto.getPrecioProd();
        }
    }

    public int getTotalVenta(){
        return totalVenta;
    }
}
