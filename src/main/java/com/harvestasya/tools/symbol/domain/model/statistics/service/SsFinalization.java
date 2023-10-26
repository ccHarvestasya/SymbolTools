package com.harvestasya.tools.symbol.domain.model.statistics.service;

import lombok.Data;

/**
 * Statistics-Service のファイナライゼーションを管理
 */
@Data
public class SsFinalization {
	/**
	 * ファイナライズブロック高
	 */
	private Long height;

	/**
	 * ファイナライズエポック
	 */
	private Long epoch;

	/**
	 * ファイナライズポイント
	 */
	private Long point;

	/**
	 * ファイナライズハッシュ
	 */
	private String hash;
}
