package com.github.venom.kernel;


interface XProcess<A> {
	void send(A msg);
}
