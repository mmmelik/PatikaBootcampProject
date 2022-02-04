package dev.melik.patikabootcampproject.repository.score;

public interface CreditScoreDAO {

    void saveCreditScore(CreditScoreEntity creditScore);

    Integer getCreditScore(Long tckn);

}
