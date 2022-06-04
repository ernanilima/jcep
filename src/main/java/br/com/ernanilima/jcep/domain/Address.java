package br.com.ernanilima.jcep.domain;

import br.com.ernanilima.jcep.utils.Utils;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(length = 36, unique = true)
    private UUID idAddress;

    @Column(length = 8, unique = true, nullable = false)
    private Integer zipCode; // cep

    @Column(length = 50, nullable = false)
    @Setter(AccessLevel.NONE)
    private String country = "Brasil"; // pais

    @Column(length = 50, nullable = false)
    private String city; // cidade

    @Column(length = 50, nullable = false)
    private String state; // unidade federativa

    @Column(length = 50, nullable = false)
    private String district; // bairro

    @Column(length = 50, nullable = false)
    private String street; // rua

    @Column(length = 50)
    private String complement; // complemento

    private Integer ibge;
    private Integer ddd;

    public Address(ViaCep viaCep) {
        this.zipCode = Utils.toInteger(viaCep.getCep());
        this.country = "Brasil";
        this.state = viaCep.getUf();
        this.city = viaCep.getLocalidade();
        this.district = viaCep.getBairro();
        this.street = viaCep.getLogradouro();
        this.complement = Utils.toString(viaCep.getComplemento());
        this.ibge = Utils.toInteger(viaCep.getIbge());
        this.ddd = Utils.toInteger(viaCep.getDdd());
    }
}
