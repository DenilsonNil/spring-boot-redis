package br.com.kualit.springredis.model.record;

import java.math.BigDecimal;

public record DepositRequest(BigDecimal amount, String accountNumber) {
}
