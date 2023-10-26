package com.harvestasya.tools.symbol.domain.model;

import java.util.Map;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

public class FinalizedBlocks {

	@Id
	private String id;

	private Map<String, Object> block;

	FinalizedBlocks(Map<String, Object> block) {
		this.block = block;
		Binary bin = (Binary) block.get("hash");
	}

	@Override
	public String toString() {
		System.out.println("Type is: " + block.getClass());
		return String.format(
				"FinalizedBlocks[id=%s, %s]",
				id, block);
	}
}
