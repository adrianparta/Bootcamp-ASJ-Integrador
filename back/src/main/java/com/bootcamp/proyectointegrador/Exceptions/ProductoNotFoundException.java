package com.bootcamp.proyectointegrador.Exceptions;

public class ProductoNotFoundException extends Exception {

    public ProductoNotFoundException() {
        super();
    }

    public ProductoNotFoundException(String message) {
        super(message);
    }

    public ProductoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductoNotFoundException(Throwable cause) {
        super(cause);
    }
}
