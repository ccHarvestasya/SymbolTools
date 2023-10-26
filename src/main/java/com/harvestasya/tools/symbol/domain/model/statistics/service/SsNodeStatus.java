package com.harvestasya.tools.symbol.domain.model.statistics.service;

import lombok.Data;

/**
 * Statistics-Service のノードステータスを管理
 */
@Data
public class SsNodeStatus {
	/**
	 * Api死活
	 */
	private String apiNode;

	/**
	 * DB死活
	 */
	private String db;
}
