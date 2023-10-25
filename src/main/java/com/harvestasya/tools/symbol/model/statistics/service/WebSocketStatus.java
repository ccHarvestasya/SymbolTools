package com.harvestasya.tools.symbol.model.statistics.service;

import lombok.Data;

/**
 * Statistics-Service のWebSocketステータスを管理
 */
@Data
public class WebSocketStatus {
	/**
	 * WebSocket死活判定
	 */
	private Boolean isAvailable;

	/**
	 * SSL WebSocket 判定
	 */
	private Boolean wss;

	/**
	 * WebSocket URL
	 */
	private String url;
}
