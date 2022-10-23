package br.com.ernanilima.jcep.service.async;

import br.com.ernanilima.jcep.builder.AddressBuilder;
import br.com.ernanilima.jcep.builder.CityBuilder;
import br.com.ernanilima.jcep.domain.Address;
import br.com.ernanilima.jcep.domain.City;
import br.com.ernanilima.jcep.repository.AddressRepository;
import br.com.ernanilima.jcep.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressAsyncTestIT {

    @InjectMocks
    private AddressAsync addressAsyncMock;

    @Mock
    private CityRepository cityRepositoryMock;

    @Mock
    private AddressRepository addressRepositoryMock;

    @Test
    void asyncSaveAddress() {
        Address address = AddressBuilder.create();
        address.getCity().setAreaCode(99);

        City cityWithoutAreaCode = CityBuilder.create();

        City cityWithAreaCode = CityBuilder.create();
        cityWithAreaCode.setAreaCode(address.getCode());

        when(cityRepositoryMock.findByCode(any())).thenReturn(Optional.of(cityWithoutAreaCode));
        when(cityRepositoryMock.save(any(City.class))).thenReturn(cityWithAreaCode);
        when(addressRepositoryMock.save(any())).thenReturn(address);

        addressAsyncMock.asyncSaveAddress(address);

        verify(cityRepositoryMock, times(1)).findByCode(any());
        verify(cityRepositoryMock, times(1)).save(any());
        verify(addressRepositoryMock, times(1)).save(any());
    }
}