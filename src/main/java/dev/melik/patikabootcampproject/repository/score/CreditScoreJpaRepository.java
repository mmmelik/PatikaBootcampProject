package dev.melik.patikabootcampproject.repository.score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface CreditScoreJpaRepository extends JpaRepository<CreditScoreEntity,Long> {

    Optional<CreditScoreEntity> findByTckn(Long tckn);

}
