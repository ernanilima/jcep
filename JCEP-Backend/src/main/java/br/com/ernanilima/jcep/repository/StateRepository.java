package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.domain.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface StateRepository extends JpaRepository<State, UUID> {
    State findByAcronym(String acronym);

    @Query("SELECT state FROM State state " +
            "JOIN state.country country " +
            "JOIN state.region region " +
            "WHERE lower(country.acronym) = lower(:countryAcronym) " +
            "AND (:regionName IS NULL OR lower(region.name) = lower(:regionName))")
    Page<State> findAllByCountry_AcronymOrCountry_AcronymAndRegion_NameIgnoreCase(
            @Param("countryAcronym") String countryAcronym,
            @Param("regionName") String regionName,
            Pageable pageable);
}
