package dev.melik.patikabootcampproject.repository.credit;


public interface CreditDAO {

    CreditEntity saveCreditApplication(CreditEntity creditEntity);

    CreditEntity getCreditById(Long id);
}
