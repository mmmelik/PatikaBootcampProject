package dev.melik.patikabootcampproject.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {


    Optional<CustomerEntity> findByTckn(Long tckn);

}
