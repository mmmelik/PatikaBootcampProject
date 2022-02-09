package dev.melik.patikabootcampproject.controller.credit;

import dev.melik.patikabootcampproject.service.credit.Credit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CreditApplicationRequest {

    @NotNull
    private Double income;

    public Credit toCredit() {
        return Credit.builder()
                .income(income)
                .build();
    }
}
