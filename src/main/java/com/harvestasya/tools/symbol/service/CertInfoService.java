package com.harvestasya.tools.symbol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harvestasya.tools.symbol.domain.entity.CertInfo;
import com.harvestasya.tools.symbol.domain.repository.CertInfoRepository;

/**
 * 証明書情報 Service
 */
@Service
public class CertInfoService {
	/**
	 * 証明書情報 Repository
	 */
	@Autowired
	CertInfoRepository certInfoRepository;
	
	public List<CertInfo> searchAll() {
		return certInfoRepository.findAll();
	}
	
	public List<CertInfo> insertAll(List<CertInfo> nodeInfoList) {
		return certInfoRepository.saveAll(nodeInfoList);
	}
	
	public CertInfo insert(CertInfo certInfo) {
		return certInfoRepository.save(certInfo);
	}
}
