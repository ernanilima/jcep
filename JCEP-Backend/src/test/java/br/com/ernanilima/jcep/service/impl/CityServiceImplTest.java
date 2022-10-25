package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.builder.CityBuilder;
import br.com.ernanilima.jcep.builder.PageableBuilder;
import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.City;
import br.com.ernanilima.jcep.param.ParamCountryAndRegionOrState;
import br.com.ernanilima.jcep.repository.CityRepository;
import br.com.ernanilima.jcep.service.exception.CityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Locale;

import static br.com.ernanilima.jcep.utils.Utils.getValueEnumType;
import static br.com.ernanilima.jcep.utils.Utils.toInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @InjectMocks
    private CityServiceImpl cityServiceMock;

    @Mock
    private CityRepository cityRepositoryMock;

    @Test
    @DisplayName("Deve retornar as cidades para a sigla do país")
    void findAllCityByCountry_Acronym_Must_Return_Cities_To_Country_Acronym() {
        Pageable pageable = PageableBuilder.create();

        List<City> cities = List.of(CityBuilder.create());
        Page<City> pageMock = new PageImpl<>(cities, pageable, cities.size());

        ParamCountryAndRegionOrState param = ParamCountryAndRegionOrState.builder().pais("BR").build();

        when(cityRepositoryMock.findAllByCountry_AcronymAndRegion_NameOrState_Acronym(any(), any(), any(), any())).thenReturn(pageMock);

        Page<ComboBox> comboBox = cityServiceMock.findAllCityByCountryOrCountryAndRegionOrCountryAndState(param, pageable);

        assertNotNull(comboBox);
        assertThat(comboBox.getTotalElements())
                .isEqualTo(cities.size());
        assertThat(comboBox.getContent().get(0).getId())
                .isEqualTo(cities.get(0).getIdCity());
        assertThat(comboBox.getContent().get(0).getValue())
                .isEqualTo(getValueEnumType(cities.get(0).getName()));
        assertThat(comboBox.getContent().get(0).getDescription())
                .isEqualTo(cities.get(0).getName());
        assertThat(toInteger(comboBox.getContent().get(0).getCode()))
                .isEqualTo(cities.get(0).getCode());
        verify(cityRepositoryMock, times(1)).findAllByCountry_AcronymAndRegion_NameOrState_Acronym(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve retornar as cidades para o nome da regiao")
    void findAllCityByCountry_AcronymAndRegion_Name_Must_Return_Cities_To_Region_Name() {
        Pageable pageable = PageableBuilder.create();

        List<City> cities = List.of(CityBuilder.create());
        Page<City> pageMock = new PageImpl<>(cities, pageable, cities.size());

        ParamCountryAndRegionOrState param = ParamCountryAndRegionOrState.builder().pais("BR").regiao("NORTE").build();

        when(cityRepositoryMock.findAllByCountry_AcronymAndRegion_NameOrState_Acronym(any(), any(), any(), any())).thenReturn(pageMock);

        Page<ComboBox> comboBox = cityServiceMock.findAllCityByCountryOrCountryAndRegionOrCountryAndState(param, pageable);

        assertNotNull(comboBox);
        assertThat(comboBox.getTotalElements())
                .isEqualTo(cities.size());
        assertThat(comboBox.getContent().get(0).getId())
                .isEqualTo(cities.get(0).getIdCity());
        assertThat(comboBox.getContent().get(0).getValue())
                .isEqualTo(getValueEnumType(cities.get(0).getName()));
        assertThat(comboBox.getContent().get(0).getDescription())
                .isEqualTo(cities.get(0).getName());
        assertThat(toInteger(comboBox.getContent().get(0).getCode()))
                .isEqualTo(cities.get(0).getCode());
        verify(cityRepositoryMock, times(1)).findAllByCountry_AcronymAndRegion_NameOrState_Acronym(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve retornar as cidades para a sigla do estado")
    void findAllCityByCountry_AcronymAndState_Acronym_Must_Return_Cities_To_State_Acronym() {
        Pageable pageable = PageableBuilder.create();

        List<City> cities = List.of(CityBuilder.create());
        Page<City> pageMock = new PageImpl<>(cities, pageable, cities.size());

        ParamCountryAndRegionOrState param = ParamCountryAndRegionOrState.builder().pais("BR").estado("PA").build();

        when(cityRepositoryMock.findAllByCountry_AcronymAndRegion_NameOrState_Acronym(any(), any(), any(), any())).thenReturn(pageMock);

        Page<ComboBox> comboBox = cityServiceMock.findAllCityByCountryOrCountryAndRegionOrCountryAndState(param, pageable);

        assertNotNull(comboBox);
        assertThat(comboBox.getTotalElements())
                .isEqualTo(cities.size());
        assertThat(comboBox.getContent().get(0).getId())
                .isEqualTo(cities.get(0).getIdCity());
        assertThat(comboBox.getContent().get(0).getValue())
                .isEqualTo(getValueEnumType(cities.get(0).getName()));
        assertThat(comboBox.getContent().get(0).getDescription())
                .isEqualTo(cities.get(0).getName());
        assertThat(toInteger(comboBox.getContent().get(0).getCode()))
                .isEqualTo(cities.get(0).getCode());
        verify(cityRepositoryMock, times(1)).findAllByCountry_AcronymAndRegion_NameOrState_Acronym(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve retornar erro por nao encontrar nenhuma cidade")
    void findAllCityByEmptyValues_Must_Return_Error_For_Not_Finding_Any_City() {
        Pageable pageable = PageableBuilder.create();

        ParamCountryAndRegionOrState param = ParamCountryAndRegionOrState.builder().pais("").regiao("").estado("").build();

        when(cityRepositoryMock.findAllByCountry_AcronymAndRegion_NameOrState_Acronym(any(), any(), any(), any())).thenReturn(Page.empty());

        Locale.setDefault(new Locale("pt", "BR"));
        CityNotFoundException exception = assertThrows(CityNotFoundException.class, () ->
                cityServiceMock.findAllCityByCountryOrCountryAndRegionOrCountryAndState(param, pageable));
        assertThat(exception.getMessage()).isEqualTo("Não foi localizado nenhuma Cidade");
    }
}