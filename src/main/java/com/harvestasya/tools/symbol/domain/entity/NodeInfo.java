package com.harvestasya.tools.symbol.domain.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
//	/**
//	 * 登録日時
//	 */
//	@Column(name = "created_at")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Date createdAt;
//
//	/**
//	 * 更新日時
//	 */
//	@Column(name = "updated_at")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Date updatedAt;

	/**
	 * ID
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * パブリックキー
	 */
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
	 * ジェネレーションハッシュ
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
}
