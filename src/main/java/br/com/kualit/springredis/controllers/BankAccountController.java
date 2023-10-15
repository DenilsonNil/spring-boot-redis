package br.com.kualit.springredis.controllers;

import br.com.kualit.springredis.model.record.*;
import br.com.kualit.springredis.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/new")
    public ResponseEntity<BankAccount> createNew(@RequestBody BankAccount bankAccount) {
        return bankAccountService.createNewBankAccount(bankAccount);
    }

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponse> createNew(@RequestBody DepositRequest request) {
        return bankAccountService.deposit(request);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transferMoney(@RequestBody TransferRequest request){
        return bankAccountService.transferMoney(request);
    }
}
