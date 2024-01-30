package com.bootcamp.proyectointegrador.Exceptions;

public class OrdenNotFoundException extends Exception {

    public OrdenNotFoundException() {
        super();
    }

    public OrdenNotFoundException(String message) {
        super(message);
    }

    public OrdenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrdenNotFoundException(Throwable cause) {
        super(cause);
    }
}
