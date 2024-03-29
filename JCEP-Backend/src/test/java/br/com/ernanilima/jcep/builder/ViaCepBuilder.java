package br.com.ernanilima.jcep.builder;

import br.com.ernanilima.jcep.domain.*;
import br.com.ernanilima.jcep.dto.ViaCepDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ViaCepBuilder {

    public static ViaCepDto create() {
        State state = StateBuilder.create();
        City city = CityBuilder.create();

        ViaCepDto dto = new ViaCepDto();
        dto.setCep("99988-771");
        dto.setUf(state.getAcronym());
        dto.setLocalidade(city.getName());
        dto.setBairro("Centro - 1");
        dto.setLogradouro("Rua Principal - 1");
        dto.setComplemento("Complemento - 1");
        dto.setIbge("8888881");
        dto.setDdd("99");

        return dto;
    }

    public static ViaCepDto createWithError() {
        ViaCepDto dto = new ViaCepDto();
        dto.setErro(Boolean.TRUE);

        return dto;
    }
}
