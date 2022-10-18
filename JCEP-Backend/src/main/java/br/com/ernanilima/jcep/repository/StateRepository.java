package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.domain.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface StateRepository extends JpaRepository<State, UUID> {
    State findByAcronym(String acronym);

    Page<State> findAllByCountry_AcronymOrRegion_NameIgnoreCase(String acronym, String name, Pageable pageable);
}
