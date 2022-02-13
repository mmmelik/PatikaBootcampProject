package dev.melik.patikabootcampproject.domain.port.cache;

import dev.melik.patikabootcampproject.domain.score.CreditScore;

import java.util.Optional;

public interface CreditScoreCachePort {

    Optional<CreditScore> getCreditScore(Long tckn);

    void saveCreditScore(Long tckn, Integer score);
}
