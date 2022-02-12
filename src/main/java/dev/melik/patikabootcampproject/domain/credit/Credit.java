package dev.melik.patikabootcampproject.domain.credit;

import dev.melik.patikabootcampproject.domain.customer.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Credit {

    private Long id;

    private Customer customer;

    private Double income;

    private Double amount;

    private CreditStatus creditStatus;

    private LocalDateTime creationDate;

    private Integer creditScore;

}
