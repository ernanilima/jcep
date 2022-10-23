package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface CityRepository extends JpaRepository<City, UUID> {
    Optional<City> findByCode(Integer code);

    @Query("SELECT city FROM City city " +
            "JOIN city.country country " +
            "JOIN city.region region " +
            "JOIN city.state state " +
            "WHERE lower(country.acronym) = lower(:countryAcronym) " +
            "AND (:regionName IS NULL OR lower(region.name) = lower(:regionName)) " +
            "AND (:stateAcronym IS NULL OR lower(state.acronym) = lower(:stateAcronym))")
    Page<City> findAllByCountry_AcronymAndRegion_NameOrState_Acronym(
            @Param("countryAcronym") String countryAcronym,
            @Param("regionName") String regionName,
            @Param("stateAcronym") String stateAcronym,
            Pageable pageable);
}
