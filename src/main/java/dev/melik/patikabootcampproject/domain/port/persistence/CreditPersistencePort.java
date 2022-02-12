package dev.melik.patikabootcampproject.domain.port.persistence;

import dev.melik.patikabootcampproject.domain.credit.Credit;
import java.util.List;

public interface CreditPersistencePort {

    Credit saveCreditApplication(Credit credit);

    Credit getCreditById(Long id);

    List<Credit> getCreditsOf(Long tckn);

}
