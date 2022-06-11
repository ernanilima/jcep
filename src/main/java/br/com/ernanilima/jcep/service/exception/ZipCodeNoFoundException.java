package br.com.ernanilima.jcep.service.exception;

/**
 * CEP nao encontrado
 */
public class ZipCodeNoFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ZipCodeNoFoundException(String message) {
        super(message);
    }
}
