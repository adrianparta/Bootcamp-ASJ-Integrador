package com.bootcamp.proyectointegrador.Exceptions;

public class ProveedorNotFoundException extends Exception {

    public ProveedorNotFoundException() {
        super();
    }

    public ProveedorNotFoundException(String message) {
        super(message);
    }

    public ProveedorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProveedorNotFoundException(Throwable cause) {
        super(cause);
    }
}
