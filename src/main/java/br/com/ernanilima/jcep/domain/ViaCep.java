package br.com.ernanilima.jcep.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViaCep {

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

}
