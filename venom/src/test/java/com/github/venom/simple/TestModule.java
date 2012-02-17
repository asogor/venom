package com.github.venom.simple;

import com.github.venom.actors.CallBackMessage;
import com.github.venom.actors.CallBackTask;
import com.github.venom.actors.Dispatcher;
import com.github.venom.actors.Key;
import com.github.venom.kernel.DispatcherProvider;
import com.github.venom.kernel.Kernel;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class TestModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Kernel.class).asEagerSingleton();
		bind(Dispatcher.class).toProvider(DispatcherProvider.class);
        bind(new TypeLiteral<Key<CallBackMessage>>() {}).toInstance(new Key<CallBackMessage>(){});
        bind(new TypeLiteral<Key<Result>>() {}).toInstance(new Key<Result>(){});
        bind(new TypeLiteral<Key<CountDown>>() {}).toInstance(new Key<CountDown>(){});
        bind(CallBackTask.class).asEagerSingleton();
        bind(KernelConfig.class).asEagerSingleton();
        bind(Finisher.class).asEagerSingleton();
        bind(Multiplier.class).asEagerSingleton();
	}

}
