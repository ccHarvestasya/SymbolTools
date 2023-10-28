package com.harvestasya.tools.symbol.domain.model.statistics.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * Statistics-Service のピアステータスを管理
 */
@Data
public class SsPeerStatus {
	/**
	 * Peer 死活判定
	 */
	private Boolean isAvailable;
	
	/**
	 * 最終チェック日時
	 */
	@DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss.SSSZ")
	private Date lastStatusCheck;
}
