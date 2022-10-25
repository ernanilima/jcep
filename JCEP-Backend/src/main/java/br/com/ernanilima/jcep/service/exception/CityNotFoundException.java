package br.com.ernanilima.jcep.service.exception;

/**
 * Cidade nao encontrada
 */
public class CityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CityNotFoundException(String message) {
        super(message);
    }
}
