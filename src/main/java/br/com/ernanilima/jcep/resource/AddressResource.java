package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.domain.Address;
import br.com.ernanilima.jcep.dto.AddressDto;
import br.com.ernanilima.jcep.service.AddressService;
import br.com.ernanilima.jcep.service.validation.ZipCodeBR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static br.com.ernanilima.jcep.utils.Utils.toInteger;

@Validated
@RestController
@RequestMapping("/endereco")
public class AddressResource {
    @Autowired
    private AddressService addressService;

    /**
     * Busca o endereco com base no cep
     * @param zipcode String
     * @return ResponseEntity<AddressDto>
     */
    @RequestMapping(value = "/cep/{zipcode}", method = RequestMethod.GET)
    public ResponseEntity<AddressDto> findByZipCode(
            @PathVariable("zipcode")
            @ZipCodeBR(message = "{invalid.zip.code}")
                    String zipcode) {

        AddressDto address = addressService.findByZipCode(toInteger(zipcode));
        return ResponseEntity.ok().body(address);
    }
}
