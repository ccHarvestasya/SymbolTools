package com.harvestasya.tools.symbol.domain.model.statistics.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * Statistics-Service のApiステータスを管理
 */
@Data
public class SsApiStatus {
	/**
	 * RESTゲートウェイURL
	 */
	private String restGatewayUrl;

	/**
	 * Api死活判定
	 */
	private Boolean isAvailable;

	/**
	 * Https有効判定
	 */
	private Boolean isHttpsEnabled;

	/**
	 * 最終チェック日時
	 */
	@DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss.SSSZ")
	private Date lastStatusCheck;

	/**
	 * WebSocketステータス
	 */
	private SsWebSocketStatus webSocket;

	/**
	 * ノードパブリックキー
	 */
	private String nodePublicKey;

	/**
	 * ブロック高
	 */
	private Long chainHeight;

	/**
	 * ファイナライゼーション
	 */
	private SsFinalization finalization;

	/**
	 * ノードステータス
	 */
	private SsNodeStatus nodeStatus;

	/**
	 * RESTバージョン
	 */
	private String restVersion;
}
