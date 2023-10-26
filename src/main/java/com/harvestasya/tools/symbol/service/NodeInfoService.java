package com.harvestasya.tools.symbol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harvestasya.tools.symbol.domain.entity.NodeInfo;
import com.harvestasya.tools.symbol.domain.repository.NodeInfoRepository;

/**
 * ノード情報 Service
 */
@Service
public class NodeInfoService {
	/**
	 * ノード情報 Repository
	 */
	@Autowired
	NodeInfoRepository nodeInfoRepository;
	
	public List<NodeInfo> searchAll() {
		return nodeInfoRepository.findAll();
	}
	
	public List<NodeInfo> insertAll(List<NodeInfo> nodeInfoList) {
		return nodeInfoRepository.saveAll(nodeInfoList);
	}
	
	public NodeInfo insert(NodeInfo nodeInfo) {
		return nodeInfoRepository.save(nodeInfo);
	}
}
