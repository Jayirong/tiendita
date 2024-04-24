package com.tienda.tiendita.Model;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "venta")
public class Venta extends RepresentationModel<Venta>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha")
    private Date fecha;

    @ManyToMany
    @JoinTable(
        name = "detalle_venta",
        joinColumns = @JoinColumn(name = "venta_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;

    
    //Getter
    public Long getId(){
        return id;
    }

    public Date getFecha(){
        return fecha;
    }

    public List<Producto> getProductos(){
        return productos;
    }
    
    //Setter
    public void setId(Long id){
        this.id = id;
    }

    public void setFecha(Date fecha){
        this.fecha = fecha;
    }

    public void setProductos(List<Producto> productos){
        this.productos = productos;
    }

}
