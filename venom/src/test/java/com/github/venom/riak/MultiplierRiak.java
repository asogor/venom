package com.github.venom.riak;

import com.github.venom.actors.CallBackMessage;
import com.github.venom.actors.Dispatcher;
import com.github.venom.actors.Key;
import com.github.venom.actors.Task;
import com.github.venom.simple.Result;
import com.google.inject.Inject;

public class MultiplierRiak implements Task<Result> {

	final private Dispatcher dispatcher;
	final private Key<CallBackMessage> timerKey;
	final private Key<Result> myKey;
	final private Key<SavedRecord> save;

	@Inject
	public MultiplierRiak(Dispatcher dispatcher, Key<CallBackMessage> timerKey,
			Key<Result> myKey, Key<SavedRecord> save) {
		super();
		this.dispatcher = dispatcher;
		this.timerKey = timerKey;
		this.myKey = myKey;
		this.save = save;
	}

	@Override
	public void invoke(Result message) {
		if (message.getPower() < 5) {
			CallBackMessage callback = new CallBackMessage(new Result(
					message.getPower() + 1, message.getValue()
							* message.getValue()), myKey, dispatcher);
			dispatcher.dispatch(timerKey, callback);
			dispatcher.dispatch(save, new SavedRecord("Awesome:" + System.nanoTime()));
		}
	}

}
