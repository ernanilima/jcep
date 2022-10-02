package br.com.ernanilima.jcep.builder;

import br.com.ernanilima.jcep.domain.Address;
import br.com.ernanilima.jcep.dto.ViaCepDto;

import java.util.UUID;

import static br.com.ernanilima.jcep.utils.Utils.toIntString;
import static br.com.ernanilima.jcep.utils.Utils.toInteger;

public class AddressBuilder {

    public static Address create() {
        ViaCepDto viaCepDto = ViaCepBuilder.create();

        return Address.builder()
                .idAddress(UUID.fromString("f266ae81-dc50-4ff4-8b30-fbb3626d0423"))
                .zipCode(toIntString(viaCepDto.getCep()))
                .country(CountryBuilder.create())
                .region(RegionBuilder.create())
                .state(StateBuilder.create())
                .city(CityBuilder.create())
                .district(viaCepDto.getBairro())
                .street(viaCepDto.getLogradouro())
                .complement(viaCepDto.getComplemento())
                .code(toInteger(viaCepDto.getIbge()))
                .build();
    }
}
