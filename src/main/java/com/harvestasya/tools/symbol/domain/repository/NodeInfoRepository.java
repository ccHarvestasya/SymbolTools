package com.harvestasya.tools.symbol.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harvestasya.tools.symbol.domain.entity.NodeInfo;

/**
 * ノード情報 Repository
 */
@Repository
public interface NodeInfoRepository extends JpaRepository<NodeInfo, Long> {
}
