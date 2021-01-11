package com.utu.codetest.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencySummaryDto {
    private String currency;
    private BigDecimal price;
    private BigDecimal day;
    private BigDecimal week;
    private BigDecimal volume;
    private BigDecimal marketCap;
}
