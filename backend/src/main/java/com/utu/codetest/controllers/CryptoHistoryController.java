package com.utu.codetest.controllers;

import com.utu.codetest.dtos.CurrencySummaryDto;
import com.utu.codetest.services.CryptoHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "/history")
public class CryptoHistoryController {
    private final CryptoHistoryService service;

    @GetMapping("/summary")
    public ResponseEntity<List<CurrencySummaryDto>> getSummary() {
        List<CurrencySummaryDto> currencySummaryDtos = service.getAllSummaries();
        return ResponseEntity.ok(currencySummaryDtos);
    }
}
