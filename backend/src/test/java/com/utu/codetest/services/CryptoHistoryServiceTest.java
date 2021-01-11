package com.utu.codetest.services;

import com.utu.codetest.dtos.CurrencySummaryDto;
import com.utu.codetest.entities.CryptoHistoryEntity;
import com.utu.codetest.repositories.CryptoHistoryRepository;
import com.utu.codetest.utils.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CryptoHistoryServiceTest {
    private final DateUtil dateUtil = new DateUtil();

    @Mock
    private CryptoHistoryRepository repository;

    private  CryptoHistoryService service;

    private final CryptoHistoryEntity mock1 = CryptoHistoryEntity.builder()
            .currency("tezos")
            .date(dateUtil.stringToDate("Dec 04, 2019"))
            .open(new BigDecimal("1.29"))
            .high(new BigDecimal("1.32"))
            .low(new BigDecimal("1.25"))
            .close(new BigDecimal("1.25"))
            .volume(new BigDecimal(46048752))
            .marketCap(new BigDecimal(824588509))
            .build();

    private final CryptoHistoryEntity mock2 = CryptoHistoryEntity.builder()
            .currency("tezos")
            .date(dateUtil.stringToDate("Dec 03, 2019"))
            .open(new BigDecimal("1.34"))
            .high(new BigDecimal("1.33"))
            .low(new BigDecimal("1.27"))
            .close(new BigDecimal("1.5"))
            .volume(new BigDecimal(46048778))
            .marketCap(new BigDecimal(904588509))
            .build();

    @BeforeEach
    void setup() {
        service = new CryptoHistoryService(repository, dateUtil);
    }

    @Test
    public void getAllSummaries() {
        when(repository.findAll()).thenReturn(List.of(mock1, mock2));
        List<CurrencySummaryDto> currencySummaryDtoList = service.getAllSummaries();
        assertEquals(1, currencySummaryDtoList.size());
        assertEquals(new BigDecimal(1.25), currencySummaryDtoList.get(0).getPrice());
        assertNull(currencySummaryDtoList.get(0).getWeek());
        assertEquals(new BigDecimal(-20).setScale(5), currencySummaryDtoList.get(0).getDay());
    }

    @Test
    public void findHistoryByCurrency() {
        when(repository.findAll()).thenReturn(List.of(mock1, mock2));
        Map<String, List<CryptoHistoryEntity>> map = service.findHistoryByCurrency();
        assertEquals(1, list.size());
    }

    @Test
    public void getCurrencySummary() {
        List<CryptoHistoryEntity> list = new ArrayList<>();
        list.add(mock1);
        list.add(mock2);
        CurrencySummaryDto currencySummaryDto = service.getCurrencySummary(list);
        assertEquals(new BigDecimal(1.25), currencySummaryDto.getPrice());
        assertNull(currencySummaryDto.getWeek());
        assertEquals(new BigDecimal(-20).setScale(5), currencySummaryDto.getDay());
    }

}
