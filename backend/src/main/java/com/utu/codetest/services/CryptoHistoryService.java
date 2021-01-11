package com.utu.codetest.services;

import com.utu.codetest.dtos.CurrencySummaryDto;
import com.utu.codetest.entities.CryptoHistoryEntity;
import com.utu.codetest.repositories.CryptoHistoryRepository;
import com.utu.codetest.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CryptoHistoryService {
    private final DateUtil dateUtil;

    private final CryptoHistoryRepository repository;

    public List<CurrencySummaryDto> getAllSummaries() {
        Map<String, List<CryptoHistoryEntity>> map = findHistoryByCurrency();
        List<CurrencySummaryDto> currencySummaryDtoList = new ArrayList<>();
        map.forEach((k, v) -> {
            currencySummaryDtoList.add(getCurrencySummary(v));
        });
        return currencySummaryDtoList;
    }

    public  Map<String, List<CryptoHistoryEntity>> findHistoryByCurrency() {
        List<CryptoHistoryEntity> cryptoHistoryEntities = repository.findAll();
        Map<String, List<CryptoHistoryEntity>> map = new HashMap<>();
        cryptoHistoryEntities.stream().forEach((c) -> {
            if(map.get(c.getCurrency()) == null) {
                List<CryptoHistoryEntity> list = new ArrayList<>();
                map.put(c.getCurrency(), list);
            }
            List<CryptoHistoryEntity> targetList = map.get(c.getCurrency());
            targetList.add(c);
        });
        return map;
    }

    public CurrencySummaryDto getCurrencySummary(List<CryptoHistoryEntity> cryptoHistoryEntityList) {
        CurrencySummaryDto currencySummaryDto = new CurrencySummaryDto();
        if(cryptoHistoryEntityList != null && cryptoHistoryEntityList.size() > 0) {
            cryptoHistoryEntityList.sort(Comparator.comparing(CryptoHistoryEntity::getDate).reversed());
            CryptoHistoryEntity latestData = cryptoHistoryEntityList.get(0);
            currencySummaryDto.setCurrency(latestData.getCurrency());
            currencySummaryDto.setPrice(latestData.getClose());
            currencySummaryDto.setMarketCap(latestData.getMarketCap());
            currencySummaryDto.setVolume(latestData.getVolume());
            Optional<CryptoHistoryEntity> daySummary = cryptoHistoryEntityList.stream()
                    .filter(c -> c.getDate().equals(dateUtil.calculate(latestData.getDate(), Calendar.DATE, -1)))
                    .findFirst();
            Optional<CryptoHistoryEntity> weekSummary = cryptoHistoryEntityList.stream()
                    .filter(c -> c.getDate().equals(dateUtil.calculate(latestData.getDate(), Calendar.DATE, -7)))
                    .findFirst();
            daySummary.ifPresent(cryptoHistoryEntity -> currencySummaryDto.setDay(
                    getPercentage(latestData.getClose(), cryptoHistoryEntity.getClose())));
            weekSummary.ifPresent(cryptoHistoryEntity -> currencySummaryDto.setDay(
                    getPercentage(latestData.getClose(), cryptoHistoryEntity.getClose())));
        }
        return currencySummaryDto;
    }

    private BigDecimal getPercentage(BigDecimal latest, BigDecimal target) {
        return latest.subtract(target)
                .divide(latest, 5, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100));
    }
}
