package dev.melik.patikabootcampproject.adapter.jpa.credit;

import dev.melik.patikabootcampproject.adapter.jpa.common.BaseEntity;
import dev.melik.patikabootcampproject.adapter.repository.CreditStatus;
import dev.melik.patikabootcampproject.adapter.jpa.customer.CustomerEntity;
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
}
