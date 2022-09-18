package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.dto.AddressDto;
import br.com.ernanilima.jcep.service.AddressService;
import br.com.ernanilima.jcep.service.validation.Language;
import br.com.ernanilima.jcep.service.validation.ZipCodeBR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static br.com.ernanilima.jcep.utils.Utils.toIntString;

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
            @PathVariable("zipcode") @ZipCodeBR(message = "{invalid.zip.code}") String zipcode,
            @RequestParam(value = "language", defaultValue = "pt_BR") @Language(message = "{invalid.language}") String language) {

        AddressDto address = addressService.findByZipCode(toIntString(zipcode));
        return ResponseEntity.ok().body(address);
    }
}
