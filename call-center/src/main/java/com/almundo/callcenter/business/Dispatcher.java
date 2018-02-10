package com.almundo.callcenter.business;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.dto.Employee;
import com.almundo.callcenter.jpa.EmployeeRepository;

/**
 * The Class Dispatcher is responsible to sends all calls received to pool threads that will execute.
 */
@Service
public class Dispatcher {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(Dispatcher.class);
	
	/** The Constant POOL_SIZE. */
	private static final int POOL_SIZE = 10;
	
	/** The Constant KEEP_ALIVE. */
	private static final int KEEP_ALIVE = 30;
	
	/** The Constant DELAY. */
	private static final int DELAY = 10000;
	
	/** The Constant RETRY_NUMBER. */
	private static final int RETRY_NUMBER = 3;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/** The executor. */
	private CallCenterThreadPoolExecutor executor; 
	
	/** The blocking queue. */
	private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(10);
	
	private EmployeesCache employeesCache;
	
	private List<Employee> employees;
	
	/**
	 * Instantiates a new dispatcher.
	 */
	public Dispatcher() {
		
	}
	
	@PostConstruct
	public void setup() {
		employees = employeeRepository.allEmployees();
		employeesCache = EmployeesCache.getInstance(employees);
		executor = new CallCenterThreadPoolExecutor(POOL_SIZE, POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, blockingQueue, employeesCache);
	}
	
	/**
	 * Method that dispatch call and retries when the queue is full.
	 *
	 * @param phoneNumber the phone number
	 * @param attemp the attemp
	 * @return the string
	 */
	public String dispatchCall(final String phoneNumber, int attemp) {
		String result = null;
		Employee emp = employeesCache.getEmployeeAvailable();
		
		try {
			
			if(emp == null) {
				throw new RejectedExecutionException();
			}
			
			final Call call = new Call(phoneNumber);
			call.setEmployee(emp);
			executor.execute(call);
		} catch(RejectedExecutionException e) {
			if(attemp <= RETRY_NUMBER) {
				LOGGER.error("Wait please " + phoneNumber);
				try {
					Thread.sleep(DELAY);
					return dispatchCall(phoneNumber, ++attemp);
				} catch (InterruptedException ex) {
					LOGGER.error(ex.getMessage());
				}
				
			}
			result = phoneNumber + " don't operators available, try later please";
			LOGGER.error(result);
		}
		
		return result;
	}
		
}
