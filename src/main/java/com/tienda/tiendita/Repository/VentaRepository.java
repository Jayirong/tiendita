package com.tienda.tiendita.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.tiendita.Model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>{
    
}
