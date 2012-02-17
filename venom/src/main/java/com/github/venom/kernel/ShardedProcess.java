package com.github.venom.kernel;

import java.util.Map;
import java.util.concurrent.Executor;

import com.github.venom.actors.Selector;
import com.github.venom.actors.Task;

class ShardedProcess<A,SHARD> implements XProcess<A> {
	protected Map<SHARD,Executor> executors;
	protected Task<A> target;
	protected Selector<SHARD, A> selector;
	
	public ShardedProcess(Task<A> target,  Selector<SHARD, A> selector, Map<SHARD,Executor> executors) {
		super();
		this.executors = executors;
		this.target = target;
		this.selector = selector;
	}
	
	public void send(final A act) {
		executors.get(selector.select(act)).execute(new Runnable() {
			@Override
			public void run() {
				target.invoke(act);
			}
		});
	}
}
