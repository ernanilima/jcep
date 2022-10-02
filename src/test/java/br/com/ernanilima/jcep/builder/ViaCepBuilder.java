package br.com.ernanilima.jcep.builder;

import br.com.ernanilima.jcep.domain.*;
import br.com.ernanilima.jcep.dto.ViaCepDto;

public class ViaCepBuilder {

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
        dto.setGia("8881");
        dto.setDdd("99");
        dto.setSiafi("7771");

        return dto;
    }
}
