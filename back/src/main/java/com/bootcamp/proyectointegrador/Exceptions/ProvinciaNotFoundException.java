package com.bootcamp.proyectointegrador.Exceptions;

public class ProvinciaNotFoundException extends Exception {

    public ProvinciaNotFoundException() {
        super();
    }

    public ProvinciaNotFoundException(String message) {
        super(message);
    }

    public ProvinciaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProvinciaNotFoundException(Throwable cause) {
        super(cause);
    }
}
