package com.github.venom.actors;

public interface Task<ACT> {
	public void invoke(ACT message);
}
