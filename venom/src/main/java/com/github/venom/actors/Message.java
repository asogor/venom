package com.github.venom.actors;

public interface Message<A> {
	public Key<A> getTo();
	public A getValue();
}
