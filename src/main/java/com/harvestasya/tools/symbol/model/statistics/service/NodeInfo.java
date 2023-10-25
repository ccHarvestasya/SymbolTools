package com.harvestasya.tools.symbol.model.statistics.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * Statistics-Service のノード情報を管理
 */
@Data
public class NodeInfo {
	/**
	 * ネットワークバージョン
	 */
	private String version;

	/**
	 * パブリックキー
	 */
	private String publicKey;

	/**
	 * ネットワークジェネレーションハッシュ
	 */
	private String networkGenerationHashSeed;

	/**
	 * ロール
	 */
	private Integer roles;

	/**
	 * ポート
	 */
	private Integer port;

	/**
	 * ネットワーク識別子
	 */
	private String networkIdentifier;

	/**
	 * ホスト名
	 */
	private String host;

	/**
	 * フレンドリー名
	 */
	private String friendlyName;

	/**
	 * 最終チェック日時
	 */
	@DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss.SSSZ")
	private Date lastAvailable;

	/**
	 * Peer ステータス
	 */
	private PeerStatus peerStatus;

	/**
	 * Api ステータス
	 */
	private ApiStatus apiStatus;
}
