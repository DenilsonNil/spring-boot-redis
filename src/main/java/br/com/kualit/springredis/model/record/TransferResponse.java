package br.com.kualit.springredis.model.record;

import java.math.BigDecimal;

public record TransferResponse (String receiverName, BigDecimal transferAmount) {
}
