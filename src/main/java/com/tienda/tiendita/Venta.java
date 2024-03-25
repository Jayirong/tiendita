package com.tienda.tiendita;

import java.util.List;
import java.time.LocalDate;

public class Venta {
    private int id;
    private LocalDate fecha;
    private List<Producto> productos;

    public Venta(int id, LocalDate fecha, List<Producto> productos){
        this.id = id;
        this.fecha = fecha;
        this.productos = productos;
    }

    public int getIdVenta(){
        return id;
    }

    public LocalDate getFechaVenta(){
        return fecha;
    }

    public List<Producto> getProductosVenta(){
        return productos;
    }
    
}
