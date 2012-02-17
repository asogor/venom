package com.github.venom.kernel;


import com.github.venom.actors.Dispatcher;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DispatcherProvider implements Provider<Dispatcher> {

	@Inject
	protected Kernel kernel;
	
	@Override
	public Dispatcher get() {
		return (Dispatcher)kernel;
	}

}
