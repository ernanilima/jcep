package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.builder.*;
import br.com.ernanilima.jcep.domain.Address;
import br.com.ernanilima.jcep.domain.Country;
import br.com.ernanilima.jcep.domain.Region;
import br.com.ernanilima.jcep.domain.State;
import br.com.ernanilima.jcep.dto.AddressDto;
import br.com.ernanilima.jcep.dto.ViaCepDto;
import br.com.ernanilima.jcep.repository.AddressRepository;
import br.com.ernanilima.jcep.repository.StateRepository;
import br.com.ernanilima.jcep.service.async.AddressAsync;
import br.com.ernanilima.jcep.service.exception.ZipCodeNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.Optional;

import static br.com.ernanilima.jcep.utils.Utils.toIntString;
import static br.com.ernanilima.jcep.utils.Utils.toInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressServiceImpl addressServiceMock;

    @Mock
    private AddressRepository addressRepositoryMock;

    @Mock
    private StateRepository stateRepositoryMock;

    @Mock
    private AddressAsync addressAsyncMock;

    @Mock
    private WebClient webClientMock;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;

    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @Test
    @DisplayName("Deve retornar um endereco encontrado no JCep")
    void findByZipCode_Must_Return_An_Address_Found_In_JCEP() {
        Optional<Address> address = Optional.of(AddressBuilder.create());

        when(addressRepositoryMock.findByZipCode(any())).thenReturn(address);

        AddressDto dto = addressServiceMock.findByZipCode("99988771");

        assertNotNull(dto);
        assertThat(dto.getZipCode()).isEqualTo(address.get().getZipCode());
        assertThat(dto.getCountry()).isEqualTo(address.get().getCountry().getName());
        assertThat(dto.getRegion()).isEqualTo(address.get().getRegion().getName());
        assertThat(dto.getState()).isEqualTo(address.get().getState().getName());
        assertThat(dto.getCity()).isEqualTo(address.get().getCity().getName());
        assertThat(dto.getDistrict()).isEqualTo(address.get().getDistrict());
        assertThat(dto.getStreet()).isEqualTo(address.get().getStreet());
        assertThat(dto.getComplement()).isEqualTo(address.get().getComplement());
        assertThat(Integer.parseInt(dto.getCode())).isEqualTo(address.get().getCode());
        assertThat(dto.getApiResult()).isEqualTo("JCep");
        verify(addressRepositoryMock, times(1)).findByZipCode(any());
    }

    @Test
    @DisplayName("Deve retornar um endereco encontrado no ViaCep")
    void findByZipCode_Must_Return_An_Address_Found_In_VIACEP() {
        ViaCepDto viaCepDto = ViaCepBuilder.create();
        Country country = CountryBuilder.create();
        State state = StateBuilder.create();
        Region region = RegionBuilder.create();

        when(addressRepositoryMock.findByZipCode(any())).thenReturn(Optional.empty());
        when(webClientMock.method(HttpMethod.GET)).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri(anyString(), (Object) any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ViaCepDto.class)).thenReturn(Mono.just(viaCepDto));

        when(stateRepositoryMock.findByAcronym(any())).thenReturn(state);

        AddressDto dto = addressServiceMock.findByZipCode("99988771");

        assertNotNull(dto);
        assertThat(dto.getZipCode()).isEqualTo(toIntString(viaCepDto.getCep()));
        assertThat(dto.getCountry()).isEqualTo(country.getName());
        assertThat(dto.getRegion()).isEqualTo(region.getName());
        assertThat(dto.getState()).isEqualTo(state.getName());
        assertThat(dto.getCity()).isEqualTo(viaCepDto.getLocalidade());
        assertThat(dto.getDistrict()).isEqualTo(viaCepDto.getBairro());
        assertThat(dto.getStreet()).isEqualTo(viaCepDto.getLogradouro());
        assertThat(dto.getComplement()).isEqualTo(viaCepDto.getComplemento());
        assertThat(toInteger(dto.getCode())).isEqualTo(toInteger(viaCepDto.getIbge()));
        assertThat(toInteger(dto.getAreaCode())).isEqualTo(toInteger(viaCepDto.getDdd()));
        assertThat(dto.getApiResult()).isEqualTo("ViaCep");

        verify(addressRepositoryMock, times(1)).findByZipCode(any());
        verify(webClientMock, times(1)).method(any());
        verify(requestBodyUriSpecMock, times(1)).uri(anyString(), (Object) any());
        verify(requestBodySpecMock, times(1)).retrieve();
        verify(responseSpecMock, times(1)).bodyToMono(ViaCepDto.class);
        verify(stateRepositoryMock, times(1)).findByAcronym(any());
    }

    @Test
    @DisplayName("Deve retornar erro por nao encontrar um endereco no JCep e no ViaCep")
    void findByZipCode_Must_Return_An_Error_For_Not_Finding_An_Address_In_JCEP_And_VIACEP() {
        ViaCepDto viaCepDto = ViaCepBuilder.createWithError();

        when(addressRepositoryMock.findByZipCode(any())).thenReturn(Optional.empty());
        when(webClientMock.method(HttpMethod.GET)).thenReturn(requestBodyUriSpecMock);
        when(requestBodyUriSpecMock.uri(anyString(), (Object) any())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ViaCepDto.class)).thenReturn(Mono.just(viaCepDto));

        Locale.setDefault(new Locale("pt", "BR"));
        ZipCodeNotFoundException exception = assertThrows(ZipCodeNotFoundException.class, () -> addressServiceMock.findByZipCode("99988771"));
        assertThat(exception.getMessage()).isEqualTo("Não localizado o CEP 99988771");
    }
}