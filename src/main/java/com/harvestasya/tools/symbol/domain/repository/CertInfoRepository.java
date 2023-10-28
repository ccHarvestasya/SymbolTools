package com.harvestasya.tools.symbol.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harvestasya.tools.symbol.domain.entity.CertInfo;

/**
 * 証明書情報 Repository
 */
@Repository
public interface CertInfoRepository extends JpaRepository<CertInfo, Long> {
}
