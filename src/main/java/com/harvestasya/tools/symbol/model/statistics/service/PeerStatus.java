package com.harvestasya.tools.symbol.model.statistics.service;

import lombok.Data;

/**
 * Statistics-Service のピアステータスを管理
 */
@Data
public class PeerStatus {
	private Boolean isAvailable;
	private Long lastStatusCheck;
}
