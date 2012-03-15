package com.github.venom.riak;

import java.io.IOException;

import org.eclipse.jetty.client.Address;
import org.eclipse.jetty.client.HttpExchange;

public class RecordPut extends HttpExchange {
	
	private final String bucket;
	private final String record;
	
	public RecordPut(String host, int port, String bucket, String record) {
		super();
		
        this.bucket = bucket;
        this.record = record;
        
		this.setMethod("POST");
		this.setAddress(new Address(host, port));
		
		String cmd = "/riak/"+bucket;
		if(record != null)
		{
			cmd += "/" + record;
		}
		this.setRequestURI(cmd);
	}

	
	public String getBucket() {
		return bucket;
	}


	public String getRecord() {
		return record;
	}	
}
