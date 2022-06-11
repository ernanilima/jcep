package br.com.ernanilima.jcep.dto;

import br.com.ernanilima.jcep.domain.Address;
import br.com.ernanilima.jcep.utils.Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String zipCode; // cep
    private String country; // pais
    private String region; // regiao
    private String state; // estado
    private String city; // cidade
    private String district; // bairro
    private String street; // rua
    private String complement; // complemento
    private String code; // ibge
    private String areaCode; // ddd

    public AddressDto(Address address) {
        this.zipCode = Utils.toString(address.getZipCode());
        this.country = address.getCountry().getName();
        this.region = address.getRegion().getName();
        this.state = address.getState().getName();
        this.city = address.getCity().getName();
        this.district = address.getDistrict();
        this.street = address.getStreet();
        this.complement = address.getComplement();
        this.code = Utils.toString(address.getCode());
        this.areaCode = Utils.toString(address.getCity().getAreaCode());
    }
}
