package com.sergeyvolkodav.model;

import java.math.BigDecimal;

public class Price {

    private BigDecimal amount;
    private BigDecimal odds;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getOdds() {
        return odds;
    }

    public void setOdds(BigDecimal odds) {
        this.odds = odds;
    }

    @Override
    public String toString() {
        return Price.class.getSimpleName() + " {" +
                "amount=" + amount +
                ", odds=" + odds +
                "}";
    }
}
