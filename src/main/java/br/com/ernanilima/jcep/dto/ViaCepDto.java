package br.com.ernanilima.jcep.dto;

import br.com.ernanilima.jcep.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static br.com.ernanilima.jcep.utils.Utils.toInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViaCepDto {

    private String cep;
    private String uf;
    private String localidade;
    private String bairro;
    private String logradouro;
    private String complemento;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
    private Boolean erro;

    public Address toAddress(State state) {
        return Address.builder()
                .zipCode(toInteger(this.cep))
                .country(state.getCountry())
                .region(state.getRegion())
                .state(state)
                .city(
                        City.builder()
                                .name(this.localidade)
                                .code(toInteger(this.ibge))
                                .country(state.getCountry())
                                .region(state.getRegion())
                                .state(state)
                                .build()
                )
                .street(this.logradouro)
                .complement(this.complemento)
                .code(toInteger(this.ibge))
                .areaCode(toInteger(this.ddd))
                .build();
    }
}
