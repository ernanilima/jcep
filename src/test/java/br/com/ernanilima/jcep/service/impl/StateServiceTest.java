package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.builder.PageableBuilder;
import br.com.ernanilima.jcep.builder.StateBuilder;
import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.State;
import br.com.ernanilima.jcep.dto.CountryOrRegionDto;
import br.com.ernanilima.jcep.repository.StateRepository;
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

import static br.com.ernanilima.jcep.utils.Utils.getValueEnumType;
import static br.com.ernanilima.jcep.utils.Utils.toInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StateServiceTest {

    @InjectMocks
    private StateServiceImpl stateServiceMock;

    @Mock
    private StateRepository stateRepositoryMock;

    @Test
    @DisplayName("Deve retornar os estados pela sigla do pais")
    void findAllRegionByCountry_Must_Return_States_By_Country_Acronym() {
        Pageable pageable = PageableBuilder.create();

        List<State> states = List.of(StateBuilder.create());
        Page<State> pageMock = new PageImpl<>(states, pageable, states.size());

        CountryOrRegionDto param = CountryOrRegionDto.builder().pais("BR").build();

        when(stateRepositoryMock.findAllByCountry_AcronymOrRegion_NameIgnoreCase(any(), any(), any())).thenReturn(pageMock);

        Page<ComboBox> comboBox = stateServiceMock.findAllStateByCountryOrRegion(param, pageable);

        assertNotNull(comboBox);
        assertThat(comboBox.getTotalElements())
                .isEqualTo(states.size());
        assertThat(comboBox.getContent().get(0).getId())
                .isEqualTo(states.get(0).getIdState());
        assertThat(comboBox.getContent().get(0).getValue())
                .isEqualTo(getValueEnumType(states.get(0).getName()));
        assertThat(comboBox.getContent().get(0).getDescription())
                .isEqualTo(states.get(0).getName());
        assertThat(comboBox.getContent().get(0).getAcronym())
                .isEqualTo(states.get(0).getAcronym());
        assertThat(toInteger(comboBox.getContent().get(0).getCode()))
                .isEqualTo(states.get(0).getCode());
        verify(stateRepositoryMock, times(1)).findAllByCountry_AcronymOrRegion_NameIgnoreCase(any(), any(), any());
    }

    @Test
    @DisplayName("Deve retornar os estados pelo nome da regiao")
    void findAllRegionByCountry_Must_Return_States_By_Region_Name() {
        Pageable pageable = PageableBuilder.create();

        List<State> states = List.of(StateBuilder.create());
        Page<State> pageMock = new PageImpl<>(states, pageable, states.size());

        CountryOrRegionDto param = CountryOrRegionDto.builder().regiao("NORTE").build();

        when(stateRepositoryMock.findAllByCountry_AcronymOrRegion_NameIgnoreCase(any(), any(), any())).thenReturn(pageMock);

        Page<ComboBox> comboBox = stateServiceMock.findAllStateByCountryOrRegion(param, pageable);

        assertNotNull(comboBox);
        assertThat(comboBox.getTotalElements())
                .isEqualTo(states.size());
        assertThat(comboBox.getContent().get(0).getId())
                .isEqualTo(states.get(0).getIdState());
        assertThat(comboBox.getContent().get(0).getValue())
                .isEqualTo(getValueEnumType(states.get(0).getName()));
        assertThat(comboBox.getContent().get(0).getDescription())
                .isEqualTo(states.get(0).getName());
        assertThat(comboBox.getContent().get(0).getAcronym())
                .isEqualTo(states.get(0).getAcronym());
        assertThat(toInteger(comboBox.getContent().get(0).getCode()))
                .isEqualTo(states.get(0).getCode());
        verify(stateRepositoryMock, times(1)).findAllByCountry_AcronymOrRegion_NameIgnoreCase(any(), any(), any());
    }
}