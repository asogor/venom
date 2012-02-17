package com.github.venom.kernel;

import java.util.concurrent.ExecutorService;

import com.github.venom.actors.Task;


public class SimpleProcess<A> implements XProcess<A>{

	protected ExecutorService executor;
	protected Task<A> target;
	
	public SimpleProcess(Task<A> target, ExecutorService executor) {
		super();
		this.executor = executor;
		this.target = target;
	}
	
	public void send(final A act) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				target.invoke(act);
			}
		});
	}

}
