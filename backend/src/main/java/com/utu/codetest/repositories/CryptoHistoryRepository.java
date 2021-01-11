package com.utu.codetest.repositories;

import com.utu.codetest.entities.CryptoHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoHistoryRepository extends JpaRepository<CryptoHistoryEntity, Long> {
}
