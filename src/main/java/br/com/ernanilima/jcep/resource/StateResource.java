package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.dto.CountryOrRegionDto;
import br.com.ernanilima.jcep.service.StateService;
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
public class StateResource {

    @Autowired
    private StateService stateService;

    /**
     * ComboBox com os estados do pais/regiao
     * @return ResponseEntity<Page<ComboBox>>
     */
    @GetMapping(value = "/estado")
    public ResponseEntity<Page<ComboBox>> findAllStateByCountryOrRegion(@Valid CountryOrRegionDto param, Pageable pageable) {
        return ResponseEntity.ok().body(stateService.findAllStateByCountryOrRegion(param, pageable));
    }
}
