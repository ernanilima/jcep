package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegionRepository extends JpaRepository<Region, UUID> {
    @Transactional(readOnly = true)
    Optional<List<Region>> findByCountry_Acronym(String acronym);
}
