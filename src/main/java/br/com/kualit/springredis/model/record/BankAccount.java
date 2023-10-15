package br.com.kualit.springredis.model.record;

import java.math.BigDecimal;

public record BankAccount(User user, String accountNumber, BigDecimal balance) {}
