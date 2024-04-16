package com.tienda.tiendita.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.tienda.tiendita.Model.Producto;
import com.tienda.tiendita.Repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{
    @Autowired
    private ProductoRepository productoRepository;

    //CRUD

    //C
    @Override
    public Producto createProducto(Producto producto){
        return productoRepository.save(producto);
    }

    //R
    @Override
    public List<Producto> getProductos(){
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(Long id){
        return productoRepository.findById(id);
    }

    //U
    @Override
    public Producto updateProducto(Long id, Producto producto){
        if (productoRepository.existsById(id)) {
            producto.setId(id);
            return productoRepository.save(producto);
        }
        else{
            return null;
        }
    }

    //D
    public void deleteProducto(Long id){
        productoRepository.deleteById(id);
    }
    
}
