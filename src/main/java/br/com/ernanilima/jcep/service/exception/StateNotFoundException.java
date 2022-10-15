package br.com.ernanilima.jcep.service.exception;

/**
 * Estado nao encontrado
 */
public class StateNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public StateNotFoundException(String message) {
        super(message);
    }
}
