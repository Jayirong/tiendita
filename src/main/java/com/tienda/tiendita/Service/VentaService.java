package com.tienda.tiendita.Service;

import java.util.List;
import java.util.Optional;

import com.tienda.tiendita.Model.Producto;
import com.tienda.tiendita.Model.Venta;

public interface VentaService {
    //CRUD
    //C
    Venta createVenta(Venta venta);
    //R
    List<Venta> getVentas();
    Optional<Venta> getVentaById(Long id);
    List<Producto> getProductosByVenta(Long ventaId);
    //U
    Venta updateVenta(Long id, Venta venta);
    Venta addProductoToVenta(Long ventaId, Long productoId);
    //D
    void deleteVenta(Long id);
}
