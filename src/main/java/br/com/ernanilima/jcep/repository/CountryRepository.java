package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {
    @Transactional(readOnly = true)
    Optional<Country> findByAcronym(String acronym);
//    Country findByAcronym(String br);
}
