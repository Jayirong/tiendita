package com.tienda.tiendita.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.tiendita.Model.Venta;
import com.tienda.tiendita.Service.VentaService;
import com.tienda.tiendita.Model.Producto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    
    @Autowired
    private VentaService ventaService;

    //C
    @PostMapping
    public Venta createVenta(@RequestBody Venta venta) {
        return ventaService.createVenta(venta);
    }

    //R All
    @GetMapping
    public List<Venta> getVentas() {
        return ventaService.getVentas();
    }

    //R Id
    @GetMapping("/{id}")
    public Optional<Venta> getVentaById(@PathVariable Long id) {
        return ventaService.getVentaById(id);
    }

    //U
    @PutMapping("/{id}")
    public Venta updateVenta(@PathVariable Long id, @RequestBody Venta venta) {
        return ventaService.updateVenta(id, venta);
    }

    //D
    @DeleteMapping("/{id}")
    public void deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
    }

    //U Productos en venta
    @PostMapping("/{ventaId}/productos/{productoId}")
    public Venta addProductoToVenta(@PathVariable Long ventaId, @PathVariable Long productoId) {
        return ventaService.addProductoToVenta(ventaId, productoId);
    }

    //R productos por venta
    @GetMapping("/{ventaId}/productos")
    public List<Producto> getProductosByVenta(@PathVariable Long ventaId) {
        return ventaService.getProductosByVenta(ventaId);
    }
}
