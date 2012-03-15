package com.github.venom.riak;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import com.github.venom.actors.CallBackMessage;
import com.github.venom.actors.CallBackTask;
import com.github.venom.actors.Dispatcher;
import com.github.venom.actors.Key;
import com.github.venom.kernel.DispatcherProvider;
import com.github.venom.kernel.Kernel;
import com.github.venom.simple.CountDown;
import com.github.venom.simple.Finisher;
import com.github.venom.simple.KernelConfig;
import com.github.venom.simple.Result;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class TestModuleRiak extends AbstractModule {

	@Override
	protected void configure() {
		/*
		 * UGLY move it to a provider
		 */
		HttpClient client = new HttpClient();
		client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
		client.setMaxConnectionsPerAddress(200); 
		client.setThreadPool(new QueuedThreadPool(250));
		client.setTimeout(30000);
		try
		{
		client.start();
		}
		catch(Exception e)
		{
			System.out.println("fck");
		}
		// UGLY
		
		bind(Kernel.class).asEagerSingleton();
		bind(Dispatcher.class).toProvider(DispatcherProvider.class);
        bind(new TypeLiteral<Key<CallBackMessage>>() {}).toInstance(new Key<CallBackMessage>(){});
        bind(new TypeLiteral<Key<Result>>() {}).toInstance(new Key<Result>(){});
        bind(new TypeLiteral<Key<CountDown>>() {}).toInstance(new Key<CountDown>(){});
        bind(new TypeLiteral<Key<SavedRecord>>() {}).toInstance(new Key<SavedRecord>(){});
        bind(CallBackTask.class).asEagerSingleton();
        bind(KernelConfigRiak.class).asEagerSingleton();
        bind(Finisher.class).asEagerSingleton();
        bind(MultiplierRiak.class).asEagerSingleton();
        bind(RiakSaver.class).asEagerSingleton();
        bind(HttpClient.class).toInstance(client);
	}

}
