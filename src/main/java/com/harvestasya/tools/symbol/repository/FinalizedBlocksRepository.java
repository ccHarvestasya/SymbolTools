package com.harvestasya.tools.symbol.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.harvestasya.tools.symbol.model.FinalizedBlocks;

public interface  FinalizedBlocksRepository extends MongoRepository<FinalizedBlocks, String> {

//	public FinalizedBlocks findByHeight(Integer height);
	
}
