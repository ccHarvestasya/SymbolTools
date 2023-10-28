package com.harvestasya.tools.symbol.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.harvestasya.tools.symbol.common.util.CertExpiration;
import com.harvestasya.tools.symbol.domain.entity.CertInfo;
import com.harvestasya.tools.symbol.domain.entity.NodeInfo;
import com.harvestasya.tools.symbol.domain.model.statistics.service.SsNodeInfo;
import com.harvestasya.tools.symbol.service.CertInfoService;
import com.harvestasya.tools.symbol.service.NodeInfoService;

@Component
public class NodeInfoAggregator {
	/**
	 * ロガー
	 */
	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * ノード情報 Service
	 */
	@Autowired
	private NodeInfoService nodeInfoService;

	/**
	 * 証明書情報 Service
	 */
	@Autowired
	private CertInfoService certInfoService;

	/**
	 * statistics-service URL
	 * statistics-serviceのURLをアプリケーションプロパティから取得
	 */
	@Value("${symbol-tools.statistics-service.url}")
	String ssURL;

	/**
	 * statistics-service からノード情報取り込み
	 * 前回の実行完了時刻から10分後に実行する（初回はアプリケーション起動直後に実行する）
	 */
	@Scheduled(fixedDelay = 600000)
	public void doAggregatNodeInfo() {
		logger.info("doAggregatNodeInfo Start");

		// Statistics-Service からノードリスト取得
		RestTemplate restTemplate = new RestTemplate();
		SsNodeInfo[] ssNodeInfoAry = restTemplate.getForObject(ssURL, SsNodeInfo[].class);

		// ノード情報セット
		List<NodeInfo> updateNodeInfoList = new ArrayList<NodeInfo>();
		for (SsNodeInfo ssNodeInfo : ssNodeInfoAry) {
			NodeInfo updateNodeInfo = new NodeInfo();

			updateNodeInfo.setPublicKey(ssNodeInfo.getPublicKey()); // パブリックキー
			updateNodeInfo.setHostName(ssNodeInfo.getHost()); // ホスト名
			updateNodeInfo.setFriendlyName(ssNodeInfo.getFriendlyName()); // フレンドリー名
			updateNodeInfo.setRoles(ssNodeInfo.getRoles()); // ロール
			updateNodeInfo.setNetworkIdentifier(ssNodeInfo.getNetworkIdentifier()); // ネットワーク識別子
			updateNodeInfo.setNetworkGenerationHashSeed(ssNodeInfo.getNetworkGenerationHashSeed()); // ジェネレーションハッシュシード
			updateNodeInfo.setVersion(ssNodeInfo.getVersion()); // ネットワークバージョン
			updateNodeInfo.setPort(ssNodeInfo.getPort()); // ポート
			updateNodeInfo.setLastAvailable(ssNodeInfo.getLastAvailable()); // 最終利用可能日時
			if (ssNodeInfo.getPeerStatus() != null) {
				updateNodeInfo.setIsAvailablePeer(ssNodeInfo.getPeerStatus().getIsAvailable()); // ピア利用可否
				updateNodeInfo.setLastStatusCheckPeer(ssNodeInfo.getPeerStatus().getLastStatusCheck()); // ピア最終利用可能日時
			}
			if (ssNodeInfo.getApiStatus() != null) {
				updateNodeInfo.setRestGatewayUrl(ssNodeInfo.getApiStatus().getRestGatewayUrl()); // RESTゲートウェイURL
				updateNodeInfo.setIsAvailableApi(ssNodeInfo.getApiStatus().getIsAvailable()); // Api最終利用可能日時
				updateNodeInfo.setIsHttpsEnabled(ssNodeInfo.getApiStatus().getIsHttpsEnabled()); // Https利用可否
				updateNodeInfo.setLastStatusCheckApi(ssNodeInfo.getApiStatus().getLastStatusCheck()); // Api最終利用可能日時
				if (ssNodeInfo.getApiStatus().getWebSocket() != null) {
					updateNodeInfo.setIsAvailableWs(ssNodeInfo.getApiStatus().getWebSocket().getIsAvailable()); // WebSocket利用可否
					updateNodeInfo.setIsWssEnabled(ssNodeInfo.getApiStatus().getWebSocket().getWss()); // SSL WebSocket利用可否
					updateNodeInfo.setWebSocketUrl(ssNodeInfo.getApiStatus().getWebSocket().getUrl()); // WebSocket URL
				}
				updateNodeInfo.setNodePublicKey(ssNodeInfo.getApiStatus().getNodePublicKey()); // ノードパブリックキー
				updateNodeInfo.setChainHeight(ssNodeInfo.getApiStatus().getChainHeight()); // ブロック高
				if (ssNodeInfo.getApiStatus().getFinalization() != null) {
					updateNodeInfo.setFinalizationHeight(ssNodeInfo.getApiStatus().getFinalization().getHeight()); // ファイナライズブロック高
					updateNodeInfo.setFinalizationEpoch(ssNodeInfo.getApiStatus().getFinalization().getEpoch()); // ファイナライズエポック
					updateNodeInfo.setFinalizationPoint(ssNodeInfo.getApiStatus().getFinalization().getPoint()); // ファイナライズポイント
					updateNodeInfo.setFinalizationHash(ssNodeInfo.getApiStatus().getFinalization().getHash()); // ファイナライズハッシュ
				}
				if (ssNodeInfo.getApiStatus().getNodeStatus() != null) {
					updateNodeInfo
							.setIsEnabledApiNode("up".equals(ssNodeInfo.getApiStatus().getNodeStatus().getApiNode())); // ApiNode利用可否
					updateNodeInfo.setIsEnabledDb("up".equals(ssNodeInfo.getApiStatus().getNodeStatus().getDb())); // DB利用可否
				}
				updateNodeInfo.setRestVersion(ssNodeInfo.getApiStatus().getRestVersion()); // RESTバージョン
			}

			updateNodeInfoList.add(updateNodeInfo);
		}

		// ノード情報テーブルに格納
		nodeInfoService.insertAll(updateNodeInfoList);

		logger.info("doAggregatNodeInfo End");
	}

	/**
	 * ノード証明書有効期限取り込み
	 * 偶数時の0分0秒に起動
	 */
	@Scheduled(cron = "0 0 */2 * * *", zone = "Asia/Tokyo")
	public void doCertExpiration() {
		logger.info("doCertExpiration Start");

		CertExpiration certExp = new CertExpiration();
		List<CertInfo> certInfoList = new ArrayList<CertInfo>();

		// テーブルからノード情報リストを取得
		List<NodeInfo> nodeInfoList = nodeInfoService.searchAll();
		for (NodeInfo nodeInfo : nodeInfoList) {
			try {
				// 証明書期限を取得
				Date certExpDate = certExp.getCertExpiration(nodeInfo.getHostName(), nodeInfo.getPort());
				// 証明書情報エンティティ編集
				CertInfo certInfo = new CertInfo();
				certInfo.setPublicKey(nodeInfo.getPublicKey()); //パブリックキー
				certInfo.setHostName(nodeInfo.getHostName()); //ホスト名
				certInfo.setCertExpiration(certExpDate); //証明書期限
				certInfoList.add(certInfo);
			} catch (Exception e) {
				logger.error(nodeInfo.getHostName() + ":" + nodeInfo.getPort() + ":" + e.getMessage());
			}
		}

		// 証明書情報テーブルに格納
		certInfoService.insertAll(certInfoList);

		logger.info("doCertExpiration End");
	}
}
