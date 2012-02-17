package com.github.venom.actors;

public class CallBackTask implements Task<CallBackMessage> {

	@Override
	public void invoke(CallBackMessage act) {
		act.call();
	}

}
