package br.com.kualit.springredis.services;

import br.com.kualit.springredis.model.entities.BankAccountEntity;
import br.com.kualit.springredis.model.record.*;
import br.com.kualit.springredis.repositories.BankAccountRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private UserService userService;

    public ResponseEntity<BankAccount> createNewBankAccount(BankAccount bankAccount) {
        val bankAccountDB = new BankAccountEntity();
        copyProperties(bankAccount, bankAccountDB);

        val user = userService.findByCpf(bankAccount.user().cpf());
        bankAccountDB.setUser(user);
        bankAccountRepository.save(bankAccountDB);

        return ResponseEntity.created(URI.create("")).body(bankAccount);
    }

    public ResponseEntity<DepositResponse> deposit(DepositRequest request) {
        val accounNumber = request.accountNumber();
        val account = bankAccountRepository.findByAccountNumber(accounNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        BigDecimal oldAmount = account.getBalance();
        BigDecimal newAmount = account.getBalance().add(request.amount());

        account.setBalance(newAmount);

        bankAccountRepository.save(account);

        return ResponseEntity.ok(new DepositResponse(oldAmount, newAmount, accounNumber));
    }

    public ResponseEntity<TransferResponse> transferMoney(TransferRequest request) {
        subtractMoney(request);
        transfer(request);
        return ResponseEntity.ok(new TransferResponse(request.receiver().name(), request.amount()));
    }

    private void transfer(TransferRequest request) {

        val receiver = userService.findByCpf(request.receiver().cpf());
        val receiverAccount = bankAccountRepository
                .findByUser(receiver).orElseThrow(() -> new RuntimeException("Account not found"));

        receiverAccount.setBalance(receiverAccount.getBalance().add(request.amount()));
        bankAccountRepository.save(receiverAccount);
    }

    private void subtractMoney(TransferRequest request) {
        val userFrom = userService.findByCpf(request.from().cpf());
        val fromAccount = bankAccountRepository
                .findByUser(userFrom).orElseThrow(() -> new RuntimeException("Account not found"));

        val newBalance = fromAccount.getBalance().subtract(request.amount());
        fromAccount.setBalance(newBalance);
        bankAccountRepository.save(fromAccount);
    }
}
