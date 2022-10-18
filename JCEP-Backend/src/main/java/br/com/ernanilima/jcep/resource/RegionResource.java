package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class RegionResource {

    @Autowired
    private RegionService regionService;

    /**
     * ComboBox com as regioes do pais
     * @return ResponseEntity<Page<ComboBox>>
     */
    @GetMapping(value = "/regiao")
    public ResponseEntity<Page<ComboBox>> findAllRegionByCountry(
            @RequestParam(value = "pais", defaultValue = "BR") String acronym, Pageable pageable) {
        return ResponseEntity.ok().body(regionService.findAllRegionByCountry(acronym, pageable));
    }
}
