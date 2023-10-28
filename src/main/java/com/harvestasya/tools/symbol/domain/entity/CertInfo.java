package com.harvestasya.tools.symbol.domain.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 証明書情報 Entity
 */
@Entity
@Data
@Table(name = "cert_info")
public class CertInfo {
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
	 * 証明書期限
	 */
	@Column(name = "cert_expiration ")
	private Date certExpiration;
}
