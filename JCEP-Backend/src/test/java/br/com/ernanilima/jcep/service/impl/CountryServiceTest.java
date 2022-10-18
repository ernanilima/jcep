package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.builder.CountryBuilder;
import br.com.ernanilima.jcep.builder.PageableBuilder;
import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.Country;
import br.com.ernanilima.jcep.repository.CountryRepository;
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
        Pageable pageable = PageableBuilder.create();

        List<Country> countries = List.of(CountryBuilder.create());
        Page<Country> pageMock = new PageImpl<>(countries, pageable, countries.size());

        when(countryRepositoryMock.findAll(pageable)).thenReturn(pageMock);

        Page<ComboBox> comboBox = countryServiceMock.findAllCountry(pageable);

        assertNotNull(comboBox);
        assertThat(comboBox.getTotalPages())
                .isEqualTo(countries.size());
        assertThat(comboBox.getContent().size())
                .isEqualTo(countries.size());
        assertThat(comboBox.getContent().get(0).getId())
                .isEqualTo(countries.get(0).getIdCountry());
        assertThat(comboBox.getContent().get(0).getValue())
                .isEqualTo(getValueEnumType(countries.get(0).getName()));
        assertThat(comboBox.getContent().get(0).getDescription())
                .isEqualTo(countries.get(0).getName());
        assertThat(comboBox.getContent().get(0).getAcronym())
                .isEqualTo(countries.get(0).getAcronym());
        assertThat(toInteger(comboBox.getContent().get(0).getCode()))
                .isEqualTo(countries.get(0).getCode());
        verify(countryRepositoryMock, times(1)).findAll(pageable);
    }
}