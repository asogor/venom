package com.github.venom.simple;

/**
 * This is a plug to wait on.
 * 
 * @author aron
 * 
 */
public class Plug extends CountDown {

	public final long end;

	public Plug(long end) {
		super();
		this.end = end;
	}
}
