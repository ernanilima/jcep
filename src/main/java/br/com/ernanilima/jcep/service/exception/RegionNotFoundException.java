package br.com.ernanilima.jcep.service.exception;

/**
 * Regiao nao encontrada
 */
public class RegionNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RegionNotFoundException(String message) {
        super(message);
    }
}
