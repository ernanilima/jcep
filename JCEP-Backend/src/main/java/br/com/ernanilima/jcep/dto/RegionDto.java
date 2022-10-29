package br.com.ernanilima.jcep.dto;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.Region;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.ernanilima.jcep.utils.Utils.getValueEnumType;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegionDto implements Serializable {

    public static Page<ComboBox> getComboBox(Page<Region> regions) {
        if (regions.isEmpty()) return null;
        List<ComboBox> comboBox = regions.stream().map(region ->
                        ComboBox.builder()
                                .id(region.getIdRegion())
                                .value(getValueEnumType(region.getName()))
                                .description(region.getName())
                                .build())
                .collect(Collectors.toList());
        return new PageImpl<>(comboBox, regions.getPageable(), regions.getTotalElements());
    }
}
