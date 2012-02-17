package com.github.venom.actors;

public interface Dispatcher {
	public <A> void dispatch(Key<A> to,A value);
}
