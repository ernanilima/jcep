package br.com.ernanilima.jcep.param;

import br.com.ernanilima.jcep.service.validation.RequiredCountryAndRegion;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@RequiredCountryAndRegion
public final class ParamCountryAndRegion {
    private String pais;
    private String regiao;
}
