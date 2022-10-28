package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.domain.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface RegionRepository extends JpaRepository<Region, UUID> {
    Page<Region> findByCountry_AcronymIgnoreCase(String acronym, Pageable pageable);
}
