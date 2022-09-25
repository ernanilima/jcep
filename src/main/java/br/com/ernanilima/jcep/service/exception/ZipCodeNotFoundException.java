package br.com.ernanilima.jcep.service.exception;

/**
 * CEP nao encontrado
 */
public class ZipCodeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ZipCodeNotFoundException(String message) {
        super(message);
    }
}
