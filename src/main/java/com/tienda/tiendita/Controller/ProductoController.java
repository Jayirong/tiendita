package com.tienda.tiendita.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.tiendita.Model.Producto;
import com.tienda.tiendita.Service.ProductoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    //C
    @PostMapping
    public EntityModel<Producto> createProducto(@RequestBody Producto producto) {
        Producto createdProducto = productoService.createProducto(producto);
        return EntityModel.of(createdProducto,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductoById(createdProducto.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductos()).withRel("all-productos"));
    }

    //R All
    //Se hace un mapeo a la lista completa de productos y se devuelve la lista de productos y el enlace a la lista completa de productos
    @GetMapping
    public CollectionModel<EntityModel<Producto>> getProductos(){
        List<Producto> productos = productoService.getProductos();

        List<EntityModel<Producto>> productoResources = productos.stream()
                    .map(producto -> EntityModel.of(producto,
                            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductoById(producto.getId())).withSelfRel()
                        ))
                    .collect(Collectors.toList());
        
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductos());
        CollectionModel<EntityModel<Producto>> resources = CollectionModel.of(productoResources, linkTo.withRel("productos"));
        return resources;
    }

    //R Id
    //A grandes rasgos el "Entity Model" vendria siendo una clase 'editable' que permite, entre otros, la creacion de hipervinculos
    @GetMapping("/{id}")
    public EntityModel<Producto> getProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoService.getProductoById(id);

        if (producto.isPresent()) {
            return EntityModel.of(producto.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductoById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductos()).withRel("all-productos"));
        } else {
            throw new ProductoNotFoundException("Objeto no encontrao con id: " + id);
        }
    }

    //U
    @PutMapping("/{id}")
    public EntityModel<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto updatedProducto = productoService.updateProducto(id, producto);
        return EntityModel.of(updatedProducto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductoById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductos()).withRel("all-productos"));
    }

    //D
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
    }
}
