package com.bootcamp.proyectointegrador.Exceptions;

public class RubroNotFoundException extends Exception {

    public RubroNotFoundException() {
        super();
    }

    public RubroNotFoundException(String message) {
        super(message);
    }

    public RubroNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RubroNotFoundException(Throwable cause) {
        super(cause);
    }
}
