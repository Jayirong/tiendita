package com.tienda.tiendita.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tienda.tiendita.Model.Venta;
import com.tienda.tiendita.Service.VentaService;

@WebMvcTest(VentaController.class)
public class VentaControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentaService ventasServiceMock;

    @Test
    public void obtenerTodasTest() throws Exception {
        Venta venta1 = new Venta();
        LocalDate fecha1 = LocalDate.of(2023, 2, 2);
        venta1.setFecha(fecha1);
        venta1.setId(1L);
        
        Venta venta2 = new Venta();
        LocalDate fecha2 = LocalDate.of(2024, 3, 5);
        venta2.setFecha(fecha2);
        venta2.setId(2L);

        List<Venta> ventas = List.of(venta1, venta2);

        //no entiendo por qué esto estaba en el ejemplo, nunca lo tomó
        // List<EntityModel<Venta>> ventasResources = ventas.stream()
        //     .map(venta -> EntityModel.of(venta))
        //     .collect(Collectors.toList());

        when(ventasServiceMock.getVentas()).thenReturn(ventas);

        mockMvc.perform(get("/ventas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.ventaList.length()").value(2))
                .andExpect(jsonPath("$._embedded.ventaList[0].fecha").value("2023-02-02"))
                .andExpect(jsonPath("$._embedded.ventaList[1].fecha").value("2024-03-05"))
                .andExpect(jsonPath("$._embedded.ventaList[0]._links.self.href").value("http://localhost/ventas/1"))
                .andExpect(jsonPath("$._embedded.ventaList[1]._links.self.href").value("http://localhost/ventas/2"));

    }

}
