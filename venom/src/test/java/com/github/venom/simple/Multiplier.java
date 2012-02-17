package com.github.venom.simple;

import com.github.venom.actors.CallBackMessage;
import com.github.venom.actors.Dispatcher;
import com.github.venom.actors.Key;
import com.github.venom.actors.Task;
import com.google.inject.Inject;

public class Multiplier implements Task<Result> {

	final private Dispatcher dispatcher;
	final private Key<CallBackMessage> timerKey;
	final private Key<Result> myKey;
	final private Key<CountDown> finish;

	@Inject
	public Multiplier(Dispatcher dispatcher, Key<CallBackMessage> timerKey,
			Key<Result> myKey, Key<CountDown> finish) {
		super();
		this.dispatcher = dispatcher;
		this.timerKey = timerKey;
		this.myKey = myKey;
		this.finish = finish;
	}

	@Override
	public void invoke(Result message) {
		if (message.getPower() < 5) {
			CallBackMessage callback = new CallBackMessage(new Result(
					message.getPower() + 1, message.getValue()
							* message.getValue()), myKey, dispatcher);
			dispatcher.dispatch(timerKey, callback);
		} else {
			dispatcher.dispatch(finish, new CountDown());
		}
	}

}
