package dev.melik.patikabootcampproject.domain.port.persistence;

import dev.melik.patikabootcampproject.adapter.jpa.score.CreditScoreEntity;
import dev.melik.patikabootcampproject.domain.score.CreditScore;

public interface CreditScorePersistencePort {

    void saveCreditScore(CreditScore creditScore);

    Integer getCreditScore(Long tckn);

}
