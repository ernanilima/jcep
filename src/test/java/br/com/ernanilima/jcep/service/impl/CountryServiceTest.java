package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.builder.CountryBuilder;
import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.Country;
import br.com.ernanilima.jcep.repository.CountryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.ernanilima.jcep.utils.Utils.getValueEnumType;
import static br.com.ernanilima.jcep.utils.Utils.toInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    @InjectMocks
    private CountryServiceImpl countryServiceMock;

    @Mock
    private CountryRepository countryRepositoryMock;

    @Test
    @DisplayName("Deve retornar os paises")
    void findAllCountry_Must_Return_The_Countries() {
        List<Country> countries = List.of(CountryBuilder.create());

        when(countryRepositoryMock.findAll()).thenReturn(countries);

        List<ComboBox> comboBox = countryServiceMock.findAllCountry();

        assertNotNull(comboBox);
        assertThat(comboBox.size()).isEqualTo(countries.size());
        assertThat(comboBox.get(0).getId()).isEqualTo(countries.get(0).getIdCountry());
        assertThat(comboBox.get(0).getValue()).isEqualTo(getValueEnumType(countries.get(0).getName()));
        assertThat(comboBox.get(0).getDescription()).isEqualTo(countries.get(0).getName());
        assertNull(comboBox.get(0).getAcronym());
        assertThat(toInteger(comboBox.get(0).getCode())).isEqualTo(countries.get(0).getCode());
        verify(countryRepositoryMock, times(1)).findAll();
    }
}