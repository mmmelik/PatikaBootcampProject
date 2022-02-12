package dev.melik.patikabootcampproject.adapter.jpa.credit;

import dev.melik.patikabootcampproject.adapter.jpa.common.BaseEntity;
import dev.melik.patikabootcampproject.domain.credit.CreditStatus;
import dev.melik.patikabootcampproject.adapter.jpa.customer.CustomerEntity;
import dev.melik.patikabootcampproject.domain.credit.Credit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "application")
public class CreditEntity extends BaseEntity {

    @ManyToOne()
    @JoinColumn
    private CustomerEntity customer;

    @Column(nullable = false)
    private Double income;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CreditStatus status;

    public static CreditEntity from(Credit credit) {
        CreditEntity creditEntity=new CreditEntity();
        creditEntity.setId(credit.getId());
        creditEntity.setIncome(credit.getIncome());
        creditEntity.setAmount(credit.getAmount());
        creditEntity.setStatus(credit.getCreditStatus());
        creditEntity.setCustomer(CustomerEntity.from(credit.getCustomer()));
        creditEntity.setCreationDate(credit.getCreationDate());
        return creditEntity;
    }


    public Credit toModel() {
        return Credit.builder()
                .id(id)
                .customer(customer.toModel())
                .income(income)
                .amount(amount)
                .creditStatus(status)
                .creationDate(creationDate)
                .build();
    }
}
