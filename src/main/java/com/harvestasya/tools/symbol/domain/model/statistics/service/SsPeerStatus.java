package com.harvestasya.tools.symbol.domain.model.statistics.service;

import lombok.Data;

/**
 * Statistics-Service のピアステータスを管理
 */
@Data
public class SsPeerStatus {
	private Boolean isAvailable;
	private Long lastStatusCheck;
}
