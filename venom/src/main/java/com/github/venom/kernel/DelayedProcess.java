package com.github.venom.kernel;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.venom.actors.Task;


public class DelayedProcess<ACT> extends SimpleProcess<ACT> {
	
	protected long delay;
	protected TimeUnit unit;
	
	public DelayedProcess(Task<ACT> target,long delay,TimeUnit unit,ScheduledExecutorService executor) {
		super(target,executor);
		this.delay = delay;
		this.unit = unit;
	}

	@Override
	public void send(final ACT act) {
		((ScheduledExecutorService)this.executor).schedule(new Runnable() {
			@Override
			public void run() {
				target.invoke(act);
			}
		}, delay, unit);
	}
	
}
