package br.com.kualit.springredis.model.record;

import java.math.BigDecimal;

public record DepositResponse(BigDecimal oldAmount, BigDecimal newAmount, String AccounNumber) {
}
