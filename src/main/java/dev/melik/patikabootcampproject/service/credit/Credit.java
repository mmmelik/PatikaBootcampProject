package dev.melik.patikabootcampproject.service.credit;

import dev.melik.patikabootcampproject.repository.credit.CreditEntity;
import dev.melik.patikabootcampproject.service.customer.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Credit {

    private Long id;

    private Customer customer;

    private Double income;

    private Double amount;

    private CreditStatus creditStatus;

    public static Credit fromEntity(CreditEntity creditEntity) {
        return Credit.builder()
                .id(creditEntity.getId())
                .income(creditEntity.getIncome())
                .amount(creditEntity.getAmount())
                .creditStatus(creditEntity.getStatus())
                .customer(Customer.fromEntity(creditEntity.getCustomer()))
                .build();
    }

    public CreditEntity toEntity() {
        CreditEntity creditEntity=new CreditEntity();
        creditEntity.setId(id);
        creditEntity.setCustomer(customer.toEntity());
        creditEntity.setIncome(income);
        creditEntity.setAmount(amount);
        creditEntity.setStatus(creditStatus);
        return creditEntity;
    }
}
