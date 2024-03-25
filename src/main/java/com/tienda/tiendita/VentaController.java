package com.tienda.tiendita;

import java.lang.module.ResolutionException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
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
        new Producto(1, "Croquetas Gato", 5000),
        new Producto(2, "Arena Gato", 8000),
        new Producto(3, "Juguete Perro", 3500),
        new Producto(4, "Cama Gato", 10000),
        new Producto(5, "Comedero Automático", 12000),
        new Producto(6, "Rascador Gato", 6000),
        new Producto(7, "Collar Perro", 2500),
        new Producto(8, "Premios Entrenamiento", 2000),
        new Producto(9, "Champú Pelo Largo", 3000),
        new Producto(10, "Juguete Catnip", 1800),
        new Producto(11, "Correa Perro", 3500),
        new Producto(12, "Cepillo Gato", 1500),
        new Producto(13, "Comida Húmeda", 4000),
        new Producto(14, "Caja Arena Gato", 9000),
        new Producto(15, "Juguete Cachorro", 2500),
        new Producto(16, "Caseta Perro", 15000),
        new Producto(17, "Transportadora Gato", 8000),
        new Producto(18, "Comida Conejos", 3000),
        new Producto(19, "Arnés Perro", 4000),
        new Producto(20, "Snacks Perro", 1500)
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

        //--V2--
        //productos
        List<Producto> productosVenta2 = new ArrayList<>();
        productosVenta2.add(productos.get(2));
        productosVenta2.add(productos.get(6));
        productosVenta2.add(productos.get(7));
        productosVenta2.add(productos.get(7));
        //fecha
        LocalDate fecha2 = LocalDate.of(2022, 6, 18);
        //objeto
        ventas.add(new Venta(2, fecha2, productosVenta2));
        
        //--V3--
        //productos
        List<Producto> productosVenta3 = new ArrayList<>();
        productosVenta3.add(productos.get(19));
        productosVenta3.add(productos.get(5));
        //fecha
        LocalDate fecha3 = LocalDate.of(2022, 8, 1);
        //objeto
        ventas.add(new Venta(3, fecha3, productosVenta3));

        //--V4--
        //productos
        List<Producto> productosVenta4 = new ArrayList<>();
        productosVenta4.add(productos.get(5));
        productosVenta4.add(productos.get(8));
        productosVenta4.add(productos.get(15));
        productosVenta4.add(productos.get(2));
        productosVenta4.add(productos.get(17));
        productosVenta4.add(productos.get(12));
        //fecha
        LocalDate fecha4 = LocalDate.of(2022, 11, 20);
        //objeto
        ventas.add(new Venta(4, fecha4, productosVenta4));

        //--V5--
        //productos
        List<Producto> productosVenta5 = new ArrayList<>();
        productosVenta5.add(productos.get(1));
        productosVenta5.add(productos.get(14));
        productosVenta5.add(productos.get(14));
        productosVenta5.add(productos.get(14));
        productosVenta5.add(productos.get(18));
        productosVenta5.add(productos.get(5));
        productosVenta5.add(productos.get(9));
        productosVenta5.add(productos.get(3));
        productosVenta5.add(productos.get(6));
        productosVenta5.add(productos.get(4));
        //fecha
        LocalDate fecha5 = LocalDate.of(2023, 2, 1);
        //objeto
        ventas.add(new Venta(5, fecha5, productosVenta5));

        //--V6--
        //productos
        List<Producto> productosVenta6 = new ArrayList<>();
        productosVenta6.add(productos.get(10));
        productosVenta6.add(productos.get(11));
        productosVenta6.add(productos.get(16));
        //fecha
        LocalDate fecha6 = LocalDate.of(2024, 1, 8);
        //objeto
        ventas.add(new Venta(6, fecha6, productosVenta6));

        //--V7--
        //productos
        List<Producto> productosVenta7 = new ArrayList<>();
        productosVenta7.add(productos.get(16));
        //fecha
        LocalDate fecha7 = LocalDate.of(2024, 1, 8);
        //objeto
        ventas.add(new Venta(7, fecha7, productosVenta7));

        //--V8--
        //productos
        List<Producto> productosVenta8 = new ArrayList<>();
        productosVenta8.add(productos.get(0));
        productosVenta8.add(productos.get(19));
        productosVenta8.add(productos.get(19));
        productosVenta8.add(productos.get(19));
        productosVenta8.add(productos.get(19));
        productosVenta8.add(productos.get(19));
        //fecha
        LocalDate fecha8 = LocalDate.of(2024, 3, 21);
        //objeto
        ventas.add(new Venta(8, fecha8, productosVenta8));
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
