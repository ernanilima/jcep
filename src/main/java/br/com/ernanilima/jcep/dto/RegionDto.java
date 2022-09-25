package br.com.ernanilima.jcep.dto;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.Region;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static br.com.ernanilima.jcep.utils.Utils.getValueEnumType;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegionDto implements Serializable {

    public static List<ComboBox> getComboBox(List<Region> regions) {
        if (regions.isEmpty()) return null;
        return regions.stream().map(region ->
                        ComboBox.builder()
                                .id(region.getIdRegion())
                                .value(getValueEnumType(region.getName()))
                                .description(region.getName())
                                .build())
                .collect(Collectors.toList());
    }
}
