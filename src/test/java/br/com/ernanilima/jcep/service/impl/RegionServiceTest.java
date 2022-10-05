package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.builder.RegionBuilder;
import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.Region;
import br.com.ernanilima.jcep.repository.RegionRepository;
import br.com.ernanilima.jcep.service.exception.RegionNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Locale;

import static br.com.ernanilima.jcep.utils.Utils.getValueEnumType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegionServiceTest {

    @InjectMocks
    private RegionServiceImpl regionServiceMock;

    @Mock
    private RegionRepository regionRepositoryMock;

    @Test
    @DisplayName("Deve retornar as regioes para o pais")
    void findAllRegionByCountry_Must_Return_The_Regions_To_The_Country() {
        List<Region> regions = List.of(RegionBuilder.create());

        when(regionRepositoryMock.findByCountry_Acronym(any())).thenReturn(regions);

        List<ComboBox> comboBox = regionServiceMock.findAllRegionByCountry("BR");

        assertNotNull(comboBox);
        assertThat(comboBox.size()).isEqualTo(regions.size());
        assertThat(comboBox.get(0).getId()).isEqualTo(regions.get(0).getIdRegion());
        assertThat(comboBox.get(0).getValue()).isEqualTo(getValueEnumType(regions.get(0).getName()));
        assertThat(comboBox.get(0).getDescription()).isEqualTo(regions.get(0).getName());
        assertNull(comboBox.get(0).getAcronym());
        assertNull(comboBox.get(0).getCode());
        verify(regionRepositoryMock, times(1)).findByCountry_Acronym(any());
    }

    @Test
    @DisplayName("Deve retornar um erro para regioes nao encontradas para o pais")
    void findAllRegionByCountry_Must_Return_An_Error_For_Regions_Not_Found_For_Country() {
        when(regionRepositoryMock.findByCountry_Acronym(any())).thenReturn(List.of());

        Locale.setDefault(new Locale("pt", "BR"));
        RegionNotFoundException exception = assertThrows(RegionNotFoundException.class, () -> regionServiceMock.findAllRegionByCountry("ER"));
        assertThat(exception.getMessage()).isEqualTo("Não foi localizado as regiões para o país ER");
    }
}