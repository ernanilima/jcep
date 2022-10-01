package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.config.annotation.RepositoryTest;
import br.com.ernanilima.jcep.domain.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
@Sql(scripts = {
        "classpath:/db/country.sql",
        "classpath:/db/region.sql",
        "classpath:/db/state.sql",
        "classpath:/db/city.sql",
        "classpath:/db/address.sql"
})
class AddressRepositoryTestIT {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName("Deve buscar o endereco pelo CEP")
    void findByZipCode_Must_Search_For_The_Address_By_The_Zip_Code() {
        Optional<Address> address = addressRepository.findByZipCode("99988771");

        assertTrue(address.isPresent());
        assertEquals(address.get().getCountry().getName(), "Nome do pais");
        assertEquals(address.get().getCountry().getAcronym(), "ZA");
        assertEquals(address.get().getCountry().getCode(), 1234);
        assertEquals(address.get().getCountry().getRegions().size(), 2);
        assertEquals(address.get().getCountry().getStates().size(), 2);
        assertEquals(address.get().getCountry().getCities().size(), 2);
        assertEquals(address.get().getRegion().getName(), "Nome da regiao - 1");
        assertNotNull(address.get().getRegion().getCountry());
        assertEquals(address.get().getRegion().getStates().size(), 1);
        assertEquals(address.get().getState().getName(), "Nome do estado - 1");
        assertEquals(address.get().getState().getAcronym(), "ZB");
        assertEquals(address.get().getState().getCode(), 91);
        assertNotNull(address.get().getState().getCountry());
        assertNotNull(address.get().getState().getRegion());
        assertEquals(address.get().getCity().getName(), "Nome da cidade - 1");
        assertEquals(address.get().getCity().getCode(), 9999991);
        assertNotNull(address.get().getCity().getCountry());
        assertNotNull(address.get().getCity().getRegion());
        assertNotNull(address.get().getCity().getState());
        assertEquals(address.get().getDistrict(), "Centro - 1");
        assertEquals(address.get().getStreet(), "Rua Principal - 1");
        assertEquals(address.get().getComplement(), "Complemento - 1");
        assertEquals(address.get().getCode(), 8888881);
    }
}