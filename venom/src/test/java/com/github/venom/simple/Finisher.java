package com.github.venom.simple;

import com.github.venom.actors.Task;

public class Finisher implements Task<CountDown> {

	private Plug plug;
	private long total = 0;

	@Override
	public void invoke(CountDown message) {
		if (message instanceof Plug) {
			this.plug = (Plug) message;
		} else {
			this.total ++;
			if (total == plug.end) {
				synchronized (plug) {
					plug.notify();
				}
			}
		}
	}

}
