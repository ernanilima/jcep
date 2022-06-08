package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    @Transactional(readOnly = true)
    Optional<Address> findByZipCode(Integer zipCode);
}
