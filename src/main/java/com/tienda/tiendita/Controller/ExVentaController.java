package com.tienda.tiendita.Controller;

import java.lang.module.ResolutionException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.tienda.tiendita.Model.Producto;
import com.tienda.tiendita.Model.Venta;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class VentaController {
    //lista de pruebas, lista de productos y ventas, cargamos los productos del tiri
    private List<Producto> productos = Arrays.asList(
        new Producto(1, "Croquetas Gato", 5000)
    );
    public List<Venta> ventas = new ArrayList<>();

    public VentaController(){
        //como vamos a usar fechas ficticias para cada venta vamos a definir el formato de dichas fechas para luego insertarlas en los objetos, porque hasta donde entendi vienen en el formato "AAAA-MM-DD"
        //y hay que definir y formatear la fecha por cada venta, debe haber una forma de hacer esto con menos codigo, pero quiero dormir
        //DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        //--V1--
        //productos
        List<Producto> productosVenta1 = new ArrayList<>();
        productosVenta1.add(productos.get(1));
        productosVenta1.add(productos.get(5));
        productosVenta1.add(productos.get(13));
        //fecha
        LocalDate fecha1 = LocalDate.of(2022, 6, 14);
        //objeto
        ventas.add(new Venta(1, fecha1, productosVenta1));
    }

    //mostramos productos en venta
    @GetMapping("productos")
    public ResponseEntity<List<Producto>> getProductos() {
        return ResponseEntity.ok(productos);
    }

    //mostramos un log con un historico de las ventas efectuadas
    @GetMapping("ventas")
    public ResponseEntity<List<Venta>> getVentas() {
        return ResponseEntity.ok(ventas);
    }

    //configuramos un metodo auxiliar para calcular el total de una venta
    private int calcularTotalVenta(Venta venta){
        int totalVenta = 0;
        for (Producto producto : venta.getProductosVenta()){
            totalVenta += producto.getPrecioProd();
        }
        return totalVenta;
    }

    //configuramos los endpoints para mostrar el total de las ventas por año, mes de un año y dia perteneciente al mes de un año
    @GetMapping("ventas/total/{anio}")
    public ResponseEntity<Integer> getTotalVentasPorAnio(@PathVariable int anio) {
        int total = 0;
        for (Venta venta : ventas){
            LocalDate fechaVenta = venta.getFechaVenta();
            if (fechaVenta.getYear() == anio){
                total += calcularTotalVenta(venta);
            }
        }
        return ResponseEntity.ok(total);
    }

    @GetMapping("ventas/total/{anio}/{mes}")
    public ResponseEntity<Integer> getTotalVentasPorMes(@PathVariable int anio, @PathVariable int mes) {
        int total = 0;
        for (Venta venta : ventas){
            LocalDate fechaVenta = venta.getFechaVenta();
            if (fechaVenta.getYear() == anio && fechaVenta.getMonthValue() == mes){
                total += calcularTotalVenta(venta);
            }
        }
        return ResponseEntity.ok(total);
    }

    @GetMapping("ventas/total/{anio}/{mes}/{dia}")
    public ResponseEntity<Integer> getTotalVentasPorDia(@PathVariable int anio, @PathVariable int mes, @PathVariable int dia) {
        int total = 0;
        for (Venta venta : ventas){
            LocalDate fechaVenta = venta.getFechaVenta();
            if (fechaVenta.getYear() == anio && fechaVenta.getMonthValue() == mes && fechaVenta.getDayOfMonth() == dia){
                total += calcularTotalVenta(venta);
            }
        }
        return ResponseEntity.ok(total);
    }

    //map de ventas y productos para que se reflejen ordenados en el JSON
    private Map<String, Object> crearMapVenta(Venta venta){
        Map<String, Object> ventaMap = new LinkedHashMap<>();
        ventaMap.put("id", venta.getIdVenta());
        ventaMap.put("fecha", venta.getFechaVenta());
        ventaMap.put("productos", venta.getProductosVenta());
        return ventaMap;
    }

    private Map<String, Object> crearMapProducto(Producto producto){
        Map<String, Object> productoMap = new LinkedHashMap<>();
        productoMap.put("id", producto.getIdProd());
        productoMap.put("nombre", producto.getNombreProd());
        productoMap.put("precio", producto.getPrecioProd());
        return productoMap;
    }


    //mostrar ventas y productos por id, como el maldito Json se desordena y sigo sin entender por que es por lo que existe el crearmapventa y crearmapproducto
    @GetMapping("ventas/{id}")
    public ResponseEntity<?> getVentaPorId(@PathVariable int id) {
        for (Venta venta : ventas){
            if (venta.getIdVenta() == id){
                Map<String, Object> ventaMap = crearMapVenta(venta);
                return ResponseEntity.ok(ventaMap);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La venta indicada no existe, enfermo de la caeza");
    }

    @GetMapping("productos/{id}")
    public ResponseEntity<?> getProductoPorId(@PathVariable int id){
        for (Producto producto : productos){
            if (producto.getIdProd() == id){
                Map<String, Object> productoMap = crearMapProducto(producto);
                return ResponseEntity.ok(productoMap);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tenemos esa cosa en stock");
    }
    
    
    
    
    

    
}
