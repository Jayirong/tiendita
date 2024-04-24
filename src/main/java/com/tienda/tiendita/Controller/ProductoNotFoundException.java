package com.tienda.tiendita.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductoNotFoundException extends RuntimeException{

    public ProductoNotFoundException(String mensaje){
        super(mensaje);
    }
    
}
