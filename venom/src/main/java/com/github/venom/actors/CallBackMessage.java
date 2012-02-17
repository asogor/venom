package com.github.venom.actors;


public class CallBackMessage {
	private Object act;
	private Key key;
	private Dispatcher d;
	
	public <A> CallBackMessage(A act, Key<A> key,Dispatcher d) {
		super();
		this.act = act;
		this.key = key;
		this.d = d;
	}
	
	public void call()
	{
		this.d.dispatch(key, act);
	}

}
