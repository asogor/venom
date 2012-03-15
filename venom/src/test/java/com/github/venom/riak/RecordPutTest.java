package com.github.venom.riak;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.testng.annotations.Test;

public class RecordPutTest {
	@Test
	public void test() throws Exception {
		HttpClient client = new HttpClient();
		client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
		client.setMaxConnectionsPerAddress(200); 
		client.setThreadPool(new QueuedThreadPool(250));
		client.setTimeout(30000); 
		client.start();

		HttpExchange exchange = (HttpExchange) new RecordPut("localhost", 8098, "test", null);

		client.send(exchange);

		System.out.println("Exchange sent");
		while (!exchange.isDone()) {
			Thread.sleep(100);
		}
		System.out.println("Exchange done");
	}
}
