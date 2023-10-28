package com.harvestasya.tools.symbol.domain.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ノード情報 Entity
 */
@Entity
@Data
@Table(name = "node_info")
public class NodeInfo {
	/**
	 * パブリックキー
	 */
	@Id
	@Column(name = "public_key")
	private String publicKey;

	/**
	 * ホスト名
	 */
	@Column(name = "host_name")
	private String hostName;

	/**
	 * フレンドリー名
	 */
	@Column(name = "friendly_name")
	private String friendlyName;

	/**
	 * ロール
	 */
	@Column(name = "roles")
	private Integer roles;

	/**
	 * ネットワーク識別子
	 */
	@Column(name = "network_identifier")
	private Integer networkIdentifier;

	/**
	 * ジェネレーションハッシュシード
	 */
	@Column(name = "network_generation_hash_seed")
	private String networkGenerationHashSeed;

	/**
	 * ネットワークバージョン
	 */
	@Column(name = "version")
	private Integer version;

	/**
	 * ポート
	 */
	@Column(name = "port")
	private Integer port;

	/**
	 * 最終利用可能日時
	 */
	@Column(name = "last_available")
	private Date lastAvailable;

	/**
	 * ピア利用可否
	 */
	@Column(name = "is_available_peer")
	private Boolean isAvailablePeer;

	/**
	 * ピア最終利用可能日時
	 */
	@Column(name = "last_status_check_peer")
	private Date lastStatusCheckPeer;

	/**
	 * RESTゲートウェイURL
	 */
	@Column(name = "rest_gateway_url")
	private String restGatewayUrl;

	/**
	 * Api利用可否
	 */
	@Column(name = "is_available_api")
	private Boolean isAvailableApi;

	/**
	 * Https利用可否
	 */
	@Column(name = "is_https_enabled")
	private Boolean isHttpsEnabled;

	/**
	 * Api最終利用可能日時
	 */
	@Column(name = "last_status_check_api")
	private Date lastStatusCheckApi;

	/**
	 * WebSocket利用可否
	 */
	@Column(name = "is_available_ws")
	private Boolean isAvailableWs;

	/**
	 * SSL WebSocket利用可否
	 */
	@Column(name = "is_wss_enabled")
	private Boolean isWssEnabled;

	/**
	 * WebSocket URL
	 */
	@Column(name = "web_socket_url")
	private String webSocketUrl;

	/**
	 * ノードパブリックキー
	 */
	@Column(name = "node_public_key")
	private String nodePublicKey;

	/**
	 * ブロック高
	 */
	@Column(name = "chain_height")
	private Long chainHeight;

	/**
	 * ファイナライズブロック高
	 */
	@Column(name = "finalization_height")
	private Long finalizationHeight;

	/**
	 * ファイナライズエポック
	 */
	@Column(name = "finalization_epoch")
	private Long finalizationEpoch;

	/**
	 * ファイナライズポイント
	 */
	@Column(name = "finalization_point")
	private Long finalizationPoint;

	/**
	 * ファイナライズハッシュ
	 */
	@Column(name = "finalization_hash")
	private String finalizationHash;

	/**
	 * ApiNode利用可否
	 */
	@Column(name = "is_enabled_api_node")
	private Boolean isEnabledApiNode;

	/**
	 * DB利用可否
	 */
	@Column(name = "is_enabled_db")
	private Boolean isEnabledDb;

	/**
	 * RESTバージョン
	 */
	@Column(name = "rest_version")
	private String restVersion;
}
