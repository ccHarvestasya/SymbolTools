package com.harvestasya.tools.symbol.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.harvestasya.tools.symbol.domain.model.FinalizedBlocks;

public interface  FinalizedBlocksRepository extends MongoRepository<FinalizedBlocks, String> {

//	public FinalizedBlocks findByHeight(Integer height);
	
}
