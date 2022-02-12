package dev.melik.patikabootcampproject.adapter.jpa.credit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CreditJpaRepository extends JpaRepository<CreditEntity, Long> {

    List<CreditEntity> findByCustomerTckn(Long tckn);


}
