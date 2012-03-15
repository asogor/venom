package com.github.venom.riak;

import java.io.IOException;

import org.eclipse.jetty.client.HttpClient;

import com.github.venom.actors.Dispatcher;
import com.github.venom.actors.Key;
import com.github.venom.actors.Task;
import com.github.venom.simple.CountDown;
import com.google.inject.Inject;

public class RiakSaver implements Task<SavedRecord> {

	final private Dispatcher dispatcher;
	final private Key<CountDown> finish;
	final private HttpClient client;

	@Inject
	public RiakSaver(Dispatcher dispatcher, Key<CountDown> finish,
			HttpClient client) {
		super();
		this.dispatcher = dispatcher;
		this.finish = finish;
		this.client = client;
	}

	@Override
	public void invoke(SavedRecord message) {
		RecordPut put = new RecordPut("localhost", 8098, "test", null) {

			@Override
			protected void onRequestComplete() throws IOException {
				super.onRequestComplete();
				System.out.println("Done Save!");
				dispatcher.dispatch(finish, new CountDown());
			}
		};
		try {
			this.client.send(put);
		} catch (Exception excp) {
			System.out.println("Ohh shxcs!");
		}
	}

}
