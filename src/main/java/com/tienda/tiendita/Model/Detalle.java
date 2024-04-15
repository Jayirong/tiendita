package com.tienda.tiendita.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle")
public class Detalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    //Getter
    public Long getId(){
        return id;
    }

    public Venta getVenta(){
        return venta;
    }

    public Producto getProducto(){
        return producto;
    }

    //Setter
    public void setId(Long id){
        this.id = id;
    }

    public void setVenta(Venta venta){
        this.venta = venta;
    }

    public void setProducto(Producto producto){
        this.producto = producto;
    }

}
