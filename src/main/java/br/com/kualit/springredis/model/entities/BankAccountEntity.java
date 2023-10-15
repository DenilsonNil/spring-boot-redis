package br.com.kualit.springredis.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "BankAccounts")
@Data
@NoArgsConstructor
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String accountNumber;

    BigDecimal balance;

    @OneToOne
    UserEntity user;
}
