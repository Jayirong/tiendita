package com.tienda.tiendita.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.tiendita.Model.Producto;
import com.tienda.tiendita.Model.Venta;
import com.tienda.tiendita.Repository.ProductoRepository;
import com.tienda.tiendita.Repository.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    //C
    @Override
    public Venta createVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    //R
    @Override
    public List<Venta> getVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> getVentaById(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public List<Producto> getProductosByVenta(Long ventaId) {
        Optional<Venta> optionalVenta = ventaRepository.findById(ventaId);

        if (optionalVenta.isPresent()) {
            Venta venta = optionalVenta.get();
            return venta.getProductos();
        } else {
            // Manejar el caso en que la venta no exista
            return null;
        }
    }

    //U
    @Override
    public Venta updateVenta(Long id, Venta venta) {
        if (ventaRepository.existsById(id)) {
            venta.setId(id);
            return ventaRepository.save(venta);
        } else {
            return null;
        }
    }

    @Override
    public Venta addProductoToVenta(Long ventaId, Long productoId) {
        Optional<Venta> optionalVenta = ventaRepository.findById(ventaId);
        Optional<Producto> optionalProducto = productoRepository.findById(productoId);

        if (optionalVenta.isPresent() && optionalProducto.isPresent()) {
            Venta venta = optionalVenta.get();
            Producto producto = optionalProducto.get();
            venta.getProductos().add(producto);
            ventaRepository.save(venta);
            return venta;
        } else {
            return null;
        }
    }

    //D
    @Override
    public void deleteVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}