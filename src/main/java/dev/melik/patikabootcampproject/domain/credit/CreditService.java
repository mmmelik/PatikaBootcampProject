package dev.melik.patikabootcampproject.domain.credit;

import java.util.List;

public interface CreditService {
    Credit apply(Long tckn, Credit credit);

    Credit getCreditById(Long tckn,Long id);

    List<Credit> getCreditsOf(Long tckn);

    Integer getCreditScore(Long tckn);
}
