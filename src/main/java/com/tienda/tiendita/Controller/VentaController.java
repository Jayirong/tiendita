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

import com.tienda.tiendita.Model.Venta;
import com.tienda.tiendita.Service.VentaService;
import com.tienda.tiendita.Model.Producto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    
    //Comentario loco: aparentemente HATEOAS es implementado en su mayoria, por no decir unicamente, en los controladores, netamente por el concepto de facilitar el acceso a la informacion al usuario

    @Autowired
    private VentaService ventaService;

    //C
    @PostMapping
    public EntityModel<Venta> createVenta(@RequestBody Venta venta) {
        Venta createdVenta = ventaService.createVenta(venta);
        return EntityModel.of(createdVenta,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentaById(createdVenta.getId())).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentas()).withRel("all-ventas"));
    }

    //R All
    @GetMapping
    public CollectionModel<EntityModel<Venta>> getVentas() {
        List<Venta> ventas = ventaService.getVentas();
        
        List<EntityModel<Venta>> ventaResources = ventas.stream()
                        .map(venta -> EntityModel.of(venta, 
                                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentaById(venta.getId())).withSelfRel()
                            ))
                        .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentas());
        CollectionModel<EntityModel<Venta>> resources = CollectionModel.of(ventaResources, linkTo.withRel("ventas"));
        return resources;
    }

    //R Id
    @GetMapping("/{id}")
    public EntityModel<Venta> getVentaById(@PathVariable Long id){
        Optional<Venta> venta = ventaService.getVentaById(id);

        if (venta.isPresent()){
            return EntityModel.of(venta.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentas()).withRel("All-Ventas"));
        } else {
            throw new VentaNotFoundException("Venta no encontrada, no esssiste una venta con id "+id);
        }

    }

    //U
    @PutMapping("/{id}")
    public EntityModel<Venta> updateVenta(@PathVariable Long id, @RequestBody Venta venta) {
        Venta updatedVenta = ventaService.updateVenta(id, venta);
        return EntityModel.of(updatedVenta,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentas()).withRel("All-Ventas"));
    }


    //D
    @DeleteMapping("/{id}")
    public void deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
    }

    //fALTA HATEOAS DE AKI PABAJO

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
