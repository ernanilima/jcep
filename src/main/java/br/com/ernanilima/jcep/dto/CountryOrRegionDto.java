package br.com.ernanilima.jcep.dto;

import br.com.ernanilima.jcep.service.validation.RequiredCountryOrRegion;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@RequiredCountryOrRegion
public final class CountryOrRegionDto {
    private String pais;
    private String regiao;
}
