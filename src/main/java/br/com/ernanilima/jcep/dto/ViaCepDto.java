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

    public Address toAddress() {
        return Address.builder()
                .zipCode(toInteger(this.cep))

//                .country(toCountry())
//                .region(toRegion())
//                .state(toState())
//                .city(toCity())

                .district(this.bairro)
                .street(this.logradouro)
                .complement(this.complemento)
                .code(toInteger(this.ibge))
                .areaCode(toInteger(this.ddd))
                .build();
    }
//
//    private Country toCountry() {
//        return Country.builder()
//                .name("Brazil")
//                .acronym("BR")
//                .code(1058)
//                .build();
//    }
//
//    private Region toRegion() {
//        return Region.builder()
//                .name("Sul")
//                .build();
//    }
//
//    private State toState() {
//        return State.builder()
//                .name("Parana")
//                .acronym("PR")
//                .code(41)
//                .build();
//    }
//
//    private City toCity() {
//        return City.builder()
//                .name("Curitiba")
//                .code(123456)
//                .build();
//    }
}
