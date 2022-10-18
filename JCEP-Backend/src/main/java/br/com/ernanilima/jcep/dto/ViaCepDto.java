package br.com.ernanilima.jcep.dto;

import br.com.ernanilima.jcep.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static br.com.ernanilima.jcep.utils.Utils.toIntString;
import static br.com.ernanilima.jcep.utils.Utils.toInteger;

@Getter
@Setter
@NoArgsConstructor
public class ViaCepDto {

    private String cep;
    private String uf;
    private String localidade;
    private String bairro;
    private String logradouro;
    private String complemento;
    private String ibge;
    private String ddd;
    private boolean erro;

    public Address toAddress(State state) {
        return Address.builder()
                .zipCode(toIntString(this.cep))
                .country(state.getCountry())
                .region(state.getRegion())
                .state(state)
                .city(
                        City.builder()
                                .name(this.localidade)
                                .code(toInteger(this.ibge))
                                .areaCode(toInteger(this.ddd))
                                .country(state.getCountry())
                                .region(state.getRegion())
                                .state(state)
                                .build()
                )
                .district(this.bairro)
                .street(this.logradouro)
                .complement(this.complemento)
                .code(toInteger(this.ibge))
                .build();
    }
}
