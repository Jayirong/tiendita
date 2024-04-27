package com.tienda.tiendita.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.tienda.tiendita.Model.Producto;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoRepositoryTest {
    @Autowired
    private ProductoRepository productoRepository;
    
    @Test
    public void guardarProductoTest(){
        Producto producto = new Producto();
        producto.setNombre("Palo");
        producto.setPrecio(500);

        Producto resultado = productoRepository.save(producto);

        assertNotNull(resultado.getId());
        assertEquals("Palo", resultado.getNombre());
        assertEquals(500, resultado.getPrecio());
    }
}
