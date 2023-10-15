package br.com.kualit.springredis.repositories;

import br.com.kualit.springredis.model.entities.BankAccountEntity;
import br.com.kualit.springredis.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {

    Optional<BankAccountEntity> findByAccountNumber(String accountNumber);

    Optional<BankAccountEntity> findByUser(UserEntity user);
}
