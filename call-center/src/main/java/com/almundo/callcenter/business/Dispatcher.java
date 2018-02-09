package com.almundo.callcenter.business;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class Dispatcher {
	
	private static final Logger LOGGER = Logger.getLogger(Dispatcher.class);
	
	private static final int POOL_SIZE = 10;
	
	private static final int KEEP_ALIVE = 30;

	private ThreadPoolExecutor executor; 
	
	private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(300);
	
	public Dispatcher() {
		executor = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, blockingQueue);
	}
	
	public String dispatchCall(final String phoneNumber) {
		String result = null;
		try {
			executor.execute(new Call(phoneNumber));
		} catch(RejectedExecutionException e) {
			result = phoneNumber + " don't lines available, try later please";
			LOGGER.error(result);
		}
		return result;
	}
}
