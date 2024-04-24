package com.tienda.tiendita.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VentaNotFoundException extends RuntimeException{

    public VentaNotFoundException(String mensaje){
        super(mensaje);
    }
    
}