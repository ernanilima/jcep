package br.com.ernanilima.jcep.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public final class CountryOrRegionDto {
    private String pais;
    private String regiao;
}
