package dev.melik.patikabootcampproject.adapter.jpa.score;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditScoreJpaRepository extends JpaRepository<CreditScoreEntity,Long> {

    Optional<CreditScoreEntity> findByTckn(Long tckn);

}
