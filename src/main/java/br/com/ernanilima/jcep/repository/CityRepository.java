package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface CityRepository extends JpaRepository<City, UUID> {
    Optional<City> findByCode(Integer code);
}
