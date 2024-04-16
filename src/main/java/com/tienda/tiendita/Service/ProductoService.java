package com.tienda.tiendita.Service;

import java.util.List;
import java.util.Optional;

import com.tienda.tiendita.Model.Producto;

public interface ProductoService {
    //CRUD
    //C
    Producto createProducto(Producto producto);
    //R
    List<Producto> getProductos();
    Optional<Producto> getProductoById(Long id);
    //U
    Producto updateProducto(Long id, Producto producto);
    //D
    void deleteProducto(Long id);
}
