package com.utu.codetest.repositories;

import com.utu.codetest.entities.CryptoHistoryEntity;
import com.utu.codetest.utils.DateUtil;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class CryptoHistoryRepositoryTest {
    @MockBean
    private DateUtil dateUtil;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private CryptoHistoryRepository cryptoHistoryRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void insertMockEmp() {
        CryptoHistoryEntity mock = CryptoHistoryEntity.builder()
                .currency("tezos")
                .date(dateUtil.stringToDate("Dec 04,2019"))
                .open(new BigDecimal("1.29"))
                .close(new BigDecimal("1.32"))
                .low(new BigDecimal("1.25"))
                .high(new BigDecimal("1.32"))
                .volume(new BigDecimal(46048752))
                .marketCap(new BigDecimal(824588509))
                .build();
        entityManager.persist(mock);
        entityManager.flush();
    }

    @Test
    public void findAll() {
        List<CryptoHistoryEntity> cryptoHistoryEntities = cryptoHistoryRepository.findAll();
        assertFalse(cryptoHistoryEntities.isEmpty());
    }
}
