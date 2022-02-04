package dev.melik.patikabootcampproject.repository.customer;

import dev.melik.patikabootcampproject.repository.common.BaseEntity;
import dev.melik.patikabootcampproject.repository.credit.CreditEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private Long tckn;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<CreditEntity> creditApplications = new java.util.ArrayList<>();
}
