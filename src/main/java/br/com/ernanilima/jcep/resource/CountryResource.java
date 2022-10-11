package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class CountryResource {

    @Autowired
    private CountryService countryService;

    /**
     * ComboBox com os paises
     * @return ResponseEntity<Page<ComboBox>>
     */
    @GetMapping(value = "/pais")
    public ResponseEntity<Page<ComboBox>> findAllCountry(Pageable pageable) {
        return ResponseEntity.ok().body(countryService.findAllCountry(pageable));
    }
}
