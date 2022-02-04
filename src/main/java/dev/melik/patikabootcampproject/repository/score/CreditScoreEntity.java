package dev.melik.patikabootcampproject.repository.score;

import dev.melik.patikabootcampproject.repository.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "credit_score")
public class CreditScoreEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private Long tckn;

    @Column(nullable = false)
    private Integer score;

}
