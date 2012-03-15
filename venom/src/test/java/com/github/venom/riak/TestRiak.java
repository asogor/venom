package com.github.venom.riak;

import com.github.venom.actors.Dispatcher;
import com.github.venom.actors.Key;
import com.github.venom.simple.CountDown;
import com.github.venom.simple.Plug;
import com.github.venom.simple.Result;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class TestRiak {

    @Inject
    protected Key<CountDown> plugKey;

    @Inject
    protected Key<Result> multiKey;
    

	@org.testng.annotations.Test
	public void runTestGuice() throws Exception {
		Injector injector = Guice.createInjector(new TestModuleRiak());
		Dispatcher dispatch = injector.getInstance(Dispatcher.class);
		injector.injectMembers(this);

		long end = 100000;
		Plug plug = new Plug(end*4);
		dispatch.dispatch(plugKey, plug);
		for (int i = 0; i < end; i++) {
			dispatch.dispatch(multiKey, new Result(1,2));
		}
		synchronized (plug) {
			plug.wait();
		}
	}
}
