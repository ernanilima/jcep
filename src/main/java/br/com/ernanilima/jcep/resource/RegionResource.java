package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/endereco")
public class RegionResource {

    @Autowired
    private RegionService regionService;

    /**
     * ComboBox com as regioes do pais
     * @return ResponseEntity<List<ComboBox>>
     */
    @GetMapping(value = "/regiao")
    public ResponseEntity<List<ComboBox>> findAllRegionByCountry(
            @RequestParam(value = "pais", defaultValue = "BR") String acronym) {
        return ResponseEntity.ok().body(regionService.findAllRegionByCountry(acronym));
    }
}
