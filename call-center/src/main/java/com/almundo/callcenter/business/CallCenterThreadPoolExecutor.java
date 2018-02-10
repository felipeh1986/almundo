package com.almundo.callcenter.business;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.almundo.callcenter.dto.Employee;
import com.almundo.callcenter.utils.EmployeeTypeEnum;

public class CallCenterThreadPoolExecutor extends ThreadPoolExecutor {

	private static final Logger LOGGER = Logger.getLogger(CallCenterThreadPoolExecutor.class);
	
	private List<Employee> employees;
	
	public CallCenterThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, List<Employee> employees) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		this.employees = Collections.synchronizedList(employees);
		LOGGER.info("CallCenterThreadPoolExecutor has been instanced");
	}
	
	@Override
    protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		
		if(employees.isEmpty()) {
			try {
				Thread.sleep(5000);
				getQueue().add(r);
			} catch (InterruptedException e) {
				LOGGER.error(e.getMessage());
			}
			throw new RejectedExecutionException("No available operators");
		}
		
		Call call = (Call) r;
        call.setEmployee(assignEmployeeToCall());
        employees.remove(call.getEmployee());	
    }
 
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        Call call = (Call) r;
        employees.add(call.getEmployee());
    }

	/**
	 * Gets the employees.
	 *
	 * @return the employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}

	/**
	 * Sets the employees.
	 *
	 * @param employees the new employees
	 */
	public void setEmployees(final List<Employee> employees) {
		this.employees = employees;
	}
    
	private synchronized Employee assignEmployeeToCall() {
		
		Employee employ = employees.parallelStream().filter(e -> e.getType().equals(EmployeeTypeEnum.OPERADOR.name())).findFirst().orElse(null);
		
		if(employ == null) {
			employ = employees.parallelStream().filter(e -> e.getType().equals(EmployeeTypeEnum.SUPERVISOR.name())).findFirst().orElse(null);
		}
		
		if(employ == null) {
			employ = employees.parallelStream().filter(e -> e.getType().equals(EmployeeTypeEnum.DIRECTOR.name())).findFirst().orElse(null);
		}
		
		return employ;
		
	}

}
