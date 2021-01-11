package com.utu.codetest.controllers;

import com.utu.codetest.dtos.CurrencySummaryDto;
import com.utu.codetest.services.CryptoHistoryService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CryptoHistoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CryptoHistoryService cryptoHistoryService;

    private final CurrencySummaryDto mockSummary = CurrencySummaryDto.builder()
            .currency("ethereum")
            .price(new BigDecimal("146.750000"))
            .day(new BigDecimal("-0.82500"))
            .week(new BigDecimal("-4.26600"))
            .volume(new BigDecimal("7865937094"))
            .marketCap(new BigDecimal("15966157442"))
            .build();
    @Test
    public void getSummary () throws Exception {
        BDDMockito.given(cryptoHistoryService.getAllSummaries()).willReturn(List.of(mockSummary));
        mockMvc.perform(get("/history/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}
