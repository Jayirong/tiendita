package com.tienda.tiendita.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha")
    private Date fecha;

    @OneToMany(mappedBy = "venta")
    private List<Detalle> detalles;

    
    //Getter
    public Long getId(){
        return id;
    }

    public Date getFecha(){
        return fecha;
    }

    public List<Detalle> getDetalles(){
        return detalles;
    }
    
    //Setter
    public void setId(Long id){
        this.id = id;
    }

    public void setFecha(Date fecha){
        this.fecha = fecha;
    }

    public void setDetalles(List<Detalle> detalles){
        this.detalles = detalles;
    }

}
