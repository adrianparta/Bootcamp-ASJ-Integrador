package com.bootcamp.proyectointegrador.Exceptions;

public class CategoriaNotFoundException extends Exception {

    public CategoriaNotFoundException() {
        super();
    }

    public CategoriaNotFoundException(String message) {
        super(message);
    }

    public CategoriaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoriaNotFoundException(Throwable cause) {
        super(cause);
    }
}
