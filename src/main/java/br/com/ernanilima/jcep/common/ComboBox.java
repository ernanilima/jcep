package br.com.ernanilima.jcep.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ComboBox {
    private UUID id;
    private String value;
    private String description;
    private String acronym;
    private String code;
}