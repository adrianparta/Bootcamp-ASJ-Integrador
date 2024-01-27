package com.bootcamp.proyectointegrador.Exceptions;

public class IvaNotFoundException extends Exception {

    public IvaNotFoundException() {
        super();
    }

    public IvaNotFoundException(String message) {
        super(message);
    }

    public IvaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IvaNotFoundException(Throwable cause) {
        super(cause);
    }
}
