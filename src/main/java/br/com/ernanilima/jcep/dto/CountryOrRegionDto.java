package br.com.ernanilima.jcep.dto;

import br.com.ernanilima.jcep.service.validation.RequiredCountryOrRegion;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@RequiredCountryOrRegion
public final class CountryOrRegionDto {
    private String pais;
    private String regiao;
}
