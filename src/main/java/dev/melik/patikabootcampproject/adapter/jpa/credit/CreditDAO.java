package dev.melik.patikabootcampproject.adapter.jpa.credit;


public interface CreditDAO {

    CreditEntity saveCreditApplication(CreditEntity creditEntity);

    CreditEntity getCreditById(Long id);
}
