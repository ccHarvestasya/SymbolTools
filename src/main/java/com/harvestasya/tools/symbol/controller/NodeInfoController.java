package com.harvestasya.tools.symbol.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.harvestasya.tools.symbol.domain.entity.CertInfo;
import com.harvestasya.tools.symbol.domain.entity.NodeInfo;
import com.harvestasya.tools.symbol.service.CertInfoService;
import com.harvestasya.tools.symbol.service.NodeInfoService;
import com.harvestasya.tools.symbol.util.CertExpiration;

/**
 * ノード情報 Controller
 */
@Controller
public class NodeInfoController {
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


	@GetMapping("/test2")
	public String displayNodeInfoList(Model model) {
		logger.info("test2 start");

		CertExpiration certExp = new CertExpiration();
		List<CertInfo> certInfoList = new ArrayList<CertInfo>();

		// テーブルからノード情報リストを取得
		List<NodeInfo> nodeInfoList = nodeInfoService.searchAll();
		for (NodeInfo nodeInfo : nodeInfoList) {
			try {
				// 証明書期限を取得
				Date certExpDate = certExp.getCertExpiration(nodeInfo.getHostName(), nodeInfo.getPort());
				logger.info(nodeInfo.getHostName() + ":" + nodeInfo.getPort() + ":" + certExpDate);
				
				// 証明書情報エンティティ編集
				CertInfo certInfo = new CertInfo();
				certInfo.setPublicKey(nodeInfo.getPublicKey()); //パブリックキー
				certInfo.setHostName(nodeInfo.getHostName());	//ホスト名
				certInfo.setCertExpiration(certExpDate);	//証明書期限
				certInfoList.add(certInfo);
			} catch (Exception e) {
				logger.error(nodeInfo.getHostName() + ":" + nodeInfo.getPort() + ":" + e.getMessage());
			}
		}
		
		// 証明書情報テーブルに格納
		certInfoService.insertAll(certInfoList);

		logger.info("test2 end");

		model.addAttribute("message", "Hello World!!");
		return "index";
	}
}
