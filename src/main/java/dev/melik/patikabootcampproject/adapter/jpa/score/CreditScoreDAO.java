package dev.melik.patikabootcampproject.adapter.jpa.score;

public interface CreditScoreDAO {

    void saveCreditScore(CreditScoreEntity creditScore);

    Integer getCreditScore(Long tckn);

}
