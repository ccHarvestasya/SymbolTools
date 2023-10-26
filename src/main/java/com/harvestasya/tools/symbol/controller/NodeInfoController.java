package com.harvestasya.tools.symbol.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.harvestasya.tools.symbol.domain.entity.NodeInfo;
import com.harvestasya.tools.symbol.domain.model.statistics.service.SsNodeInfo;
import com.harvestasya.tools.symbol.service.NodeInfoService;

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

	// statistics-serviceのURLをアプリケーションプロパティから取得
	@Value("${symbol-tools.statistics-service.url}")
	String URL;

	@GetMapping("/test2")
	public String displayNodeInfoList(Model model) {
		logger.info("access GET sample");
		
		// Statistics-Service からノードリスト取得
		RestTemplate restTemplate = new RestTemplate();
		SsNodeInfo[] ssNodeInfoAry = restTemplate.getForObject(URL, SsNodeInfo[].class);

		List<NodeInfo> updateNodeInfoList = new ArrayList<NodeInfo>();
		for (SsNodeInfo ssNodeInfo : ssNodeInfoAry) {
			NodeInfo updateNodeInfo = new NodeInfo();
			updateNodeInfo.setPublicKey(ssNodeInfo.getPublicKey());
			updateNodeInfo.setHostName(ssNodeInfo.getHost());
			
			updateNodeInfoList.add(updateNodeInfo);
			
			System.out.println(ssNodeInfo.getVersion());
			System.out.println(ssNodeInfo.getLastAvailable());
			if (ssNodeInfo.getPeerStatus() != null) {
				System.out.println(ssNodeInfo.getPeerStatus().getIsAvailable());
			}
		}
		
		nodeInfoService.insertAll(updateNodeInfoList);
		
		List<NodeInfo> nodeInfoList = nodeInfoService.searchAll();
		model.addAttribute("message", "Hello World!!");
		return "index";
	}
}
