package com.almundo.callcenter.business;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.almundo.callcenter.utils.DataProviderTest;

//@RunWith(ConcurrentTestRunner.class)
public class CallCenterThreadPoolExecutorTest {

	private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(10);

	private EmployeesCache cache;

	private CallCenterThreadPoolExecutor executor;

	@Before
	public void setup() {
		cache = EmployeesCache.getInstance(DataProviderTest.buildEmployees());
		executor = new CallCenterThreadPoolExecutor(10, 10, 30, TimeUnit.SECONDS, blockingQueue, cache);
	}

	@Test
	public void executor_test() {
		for (int i = 0; i < 10; i++) {
			executor.execute(buildCall());
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Call buildCall() {
		final Call call = new Call(DataProviderTest.generateRandomNumber().toString());
		call.setEmployee(cache.getEmployeeAvailable());
		return call;
	}

}
