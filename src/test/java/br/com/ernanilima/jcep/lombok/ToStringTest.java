package br.com.ernanilima.jcep.lombok;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.*;
import br.com.ernanilima.jcep.resource.exception.StandardError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ToStringTest {

    private UUID uuid;

    @BeforeEach
    void setup() {
        this.uuid = UUID.randomUUID();
    }

    @Test
    void country_Builder_toString() {
        String country = Country.builder().idCountry(this.uuid).toString();
        assertTrue(country.contains(this.uuid.toString()));
    }

    @Test
    void region_Builder_toString() {
        String region = Region.builder().idRegion(this.uuid).toString();
        assertTrue(region.contains(this.uuid.toString()));
    }

    @Test
    void state_Builder_toString() {
        String state = State.builder().idState(this.uuid).toString();
        assertTrue(state.contains(this.uuid.toString()));
    }

    @Test
    void city_Builder_toString() {
        String city = City.builder().idCity(this.uuid).toString();
        assertTrue(city.contains(this.uuid.toString()));
    }

    @Test
    void address_Builder_toString() {
        String address = Address.builder().idAddress(this.uuid).toString();
        assertTrue(address.contains(this.uuid.toString()));
    }

    @Test
    void standardError_Builder_toString() {
        String standardError = StandardError.builder().message(this.uuid.toString()).toString();
        assertTrue(standardError.contains(this.uuid.toString()));
    }

    @Test
    void comboBox_Builder_toString() {
        String comboBox = ComboBox.builder().id(this.uuid).toString();
        assertTrue(comboBox.contains(this.uuid.toString()));
    }
}