package dev.melik.patikabootcampproject.service.credit;

import dev.melik.patikabootcampproject.service.customer.Customer;

import java.util.List;

public interface CreditService {
    Credit apply(Long tckn, Credit credit);

    Credit getCreditById(Long tckn,Long id);

    List<Credit> getCreditsOf(Long tckn);

    Integer getCreditScore(Long tckn);
}
