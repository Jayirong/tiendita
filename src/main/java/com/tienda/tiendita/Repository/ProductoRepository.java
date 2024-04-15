package com.tienda.tiendita.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.tiendita.Model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
}
