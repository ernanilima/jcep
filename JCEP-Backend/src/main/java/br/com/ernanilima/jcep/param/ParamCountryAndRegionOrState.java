package br.com.ernanilima.jcep.param;

import br.com.ernanilima.jcep.service.validation.RequiredCountry;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@RequiredCountry(message = "{exc.required.country.and.region.or.state}")
public final class ParamCountryAndRegionOrState implements ParamCountry {
    private String pais;
    private String regiao;
    private String estado;
}
