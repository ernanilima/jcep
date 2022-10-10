package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class CountryResource {

    @Autowired
    private CountryService countryService;

    /**
     * ComboBox com os paises
     * @return ResponseEntity<List<ComboBox>>
     */
    @GetMapping(value = "/pais")
    public ResponseEntity<List<ComboBox>> findAllCountry() {
        return ResponseEntity.ok().body(countryService.findAllCountry());
    }
}
