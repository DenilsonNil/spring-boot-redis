package br.com.kualit.springredis.model.record;

import java.math.BigDecimal;

public record TransferRequest(User from, User receiver, BigDecimal amount) {
}
