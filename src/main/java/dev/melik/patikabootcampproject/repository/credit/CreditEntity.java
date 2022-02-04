package dev.melik.patikabootcampproject.repository.credit;

import dev.melik.patikabootcampproject.repository.common.BaseEntity;
import dev.melik.patikabootcampproject.repository.customer.CustomerEntity;
import dev.melik.patikabootcampproject.service.credit.CreditStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
}
