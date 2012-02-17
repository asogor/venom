package com.github.venom.kernel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.venom.actors.Dispatcher;
import com.github.venom.actors.Key;
import com.github.venom.actors.Selector;
import com.github.venom.actors.Task;

public class Kernel implements Dispatcher {

	protected Map<Key, XProcess> procs = new ConcurrentHashMap<Key, XProcess>();

	public Kernel() {
		super();
	}

	public <A> void dispatch(Key<A> key, A message) {
		@SuppressWarnings("unchecked")
		XProcess<A> p = procs.get(key);
		p.send(message);
	}

	public <A> void registerProcess(Key<A> key, Task<A> task,
			ExecutorService executor) {
		procs.put(key, new SimpleProcess(task, executor));
	}

	public <A> void registerProcess(Key<A> key, Task<A> task, long delay,
			TimeUnit tu, ScheduledExecutorService executor) {
		procs.put(key, new DelayedProcess(task, delay, tu, executor));
	}

	public <A, SHARD> void registerProcess(Key<A> key, Task<A> task,
			Selector<SHARD, A> selector, Map<SHARD, Executor> executors) {
		procs.put(key, new ShardedProcess<A, SHARD>(task, selector, executors));
	}
}
