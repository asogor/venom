package com.github.venom.simple;

import com.github.venom.actors.Dispatcher;
import com.github.venom.actors.Key;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class TestSimple {

    @Inject
    protected Key<CountDown> plugKey;

    @Inject
    protected Key<Result> multiKey;
    

	@org.testng.annotations.Test
	public void runTestGuice() throws Exception {
		Injector injector = Guice.createInjector(new TestModule());
		Dispatcher dispatch = injector.getInstance(Dispatcher.class);
		injector.injectMembers(this);

		long end = 1000000;
		Plug plug = new Plug(end);
		dispatch.dispatch(plugKey, plug);
		for (int i = 0; i < end; i++) {
			dispatch.dispatch(multiKey, new Result(1,2));
		}
		synchronized (plug) {
			plug.wait();
		}
	}
}
