package com.github.venom.actors;

public interface Selector<SHARD,VALUE> {
	public SHARD select(VALUE value);
}
