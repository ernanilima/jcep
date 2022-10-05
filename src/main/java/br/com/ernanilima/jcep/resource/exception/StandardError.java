package br.com.ernanilima.jcep.resource.exception;

import lombok.*;

import java.io.Serializable;

/**
 * Modelo padrao do erro para exibir
 */

@AllArgsConstructor
@Getter
@Builder
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
