package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.domain.ViaCep;
import br.com.ernanilima.jcep.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ViaCep> findByZipCode(@PathVariable String zipcode) {
        ViaCep viaCep = addressService.findByZipCode(zipcode);
        return ResponseEntity.ok().body(viaCep);
    }
}
