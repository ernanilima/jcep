package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.param.ParamCountryAndRegionOrState;
import br.com.ernanilima.jcep.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class CityResource {

    @Autowired
    private CityService cityService;

    /**
     * ComboBox com as cidades do pais/regiao/estado
     * @return ResponseEntity<Page<ComboBox>>
     */
    @GetMapping(value = "/cidade")
    public ResponseEntity<Page<ComboBox>> findAllCityByCountryOrCountryAndRegionOrCountryAndState(
            @Valid ParamCountryAndRegionOrState param, Pageable pageable) {
        return ResponseEntity.ok().body(cityService.findAllCityByCountryOrCountryAndRegionOrCountryAndState(param, pageable));
    }
}
