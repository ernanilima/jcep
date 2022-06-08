package br.com.ernanilima.jcep.domain.br;

import br.com.ernanilima.jcep.domain.Country;
import br.com.ernanilima.jcep.domain.Region;
import br.com.ernanilima.jcep.domain.State;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static br.com.ernanilima.jcep.utils.Utils.toInteger;

@Getter
@AllArgsConstructor
public enum Estado {

    RONDONIA("Rondônia", "RO", "11", Regiao.NORTE.getName()),
    ACRE("Acre", "AC", "12", Regiao.NORTE.getName()),
    AMAZONAS("Amazonas", "AM", "13", Regiao.NORTE.getName()),
    RORAIMA("Roraima", "RR", "14", Regiao.NORTE.getName()),
    PARA("Pará", "PA", "15", Regiao.NORTE.getName()),
    AMAPA("Amapá", "AP", "16", Regiao.NORTE.getName()),
    TOCANTIS("Tocantins", "TO", "17", Regiao.NORTE.getName()),

    MARANHAO("Maranhão", "MA", "21", Regiao.NORDESTE.getName()),
    PIAUI("Piauí", "PI", "22", Regiao.NORDESTE.getName()),
    CEARA("Ceará", "CE", "23", Regiao.NORDESTE.getName()),
    RIO_GRANDE_DO_NORTE("Rio Grande do Norte", "RN", "24", Regiao.NORDESTE.getName()),
    PARAIBA("Paraíba", "PB", "25", Regiao.NORDESTE.getName()),
    PERNAMBUCO("Pernambuco", "PE", "26", Regiao.NORDESTE.getName()),
    ALAGOAS("Alagoas", "AL", "27", Regiao.NORDESTE.getName()),
    SERGIPE("Sergipe", "SE", "28", Regiao.NORDESTE.getName()),
    BAHIA("Bahia", "BA", "29", Regiao.NORDESTE.getName()),

    MINAS_GERAIS("Minas Gerais", "MG", "31", Regiao.SUDESTE.getName()),
    ESPIRITO_SANTO("Espírito Santo", "ES", "32", Regiao.SUDESTE.getName()),
    RIO_DE_JANEIRO("Rio de Janeiro", "RJ", "33", Regiao.SUDESTE.getName()),
    SAO_PAULO("São Paulo", "SP", "35", Regiao.SUDESTE.getName()),

    PARANA("Paraná", "PR", "41", Regiao.SUL.getName()),
    SANTA_CATARINA("Santa Catarina", "SC", "42", Regiao.SUL.getName()),
    RIO_GRANDE_DO_SUL("Rio Grande do Sul", "RS", "43", Regiao.SUL.getName()),

    MATO_GROSSO_DO_SUL("Mato Grosso do Sul", "MS", "50", Regiao.CENTRO_OESTE.getName()),
    MATO_GROSSO("Mato Grosso", "MT", "51", Regiao.CENTRO_OESTE.getName()),
    GOIAIS("Goiás", "GO", "52", Regiao.CENTRO_OESTE.getName()),
    DISTRITO_FEDERAL("Distrito Federal", "DF", "53", Regiao.CENTRO_OESTE.getName());

    private String name;
    private String acronym;
    private String code;
    private String region;

    public static List<State> getStates(Country country, List<Region> regions) {
        List<State> states = new ArrayList<>();
        for (Estado value : Estado.values()) {
            states.add(
                    State.builder()
                            .name(value.getName())
                            .acronym(value.getAcronym())
                            .code(toInteger(value.getCode()))
                            .country(country)
                            .region(regions.stream().filter(region -> region.getName().equals(value.getRegion())).findFirst().orElse(null))
                        .build()
            );
        }
        return states;
    }
}
