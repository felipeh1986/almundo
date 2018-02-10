package com.almundo.callcenter.business;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * The Class CallCenterThreadPoolExecutor is a custom thread pool executor.
 */
public class CallCenterThreadPoolExecutor extends ThreadPoolExecutor {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(CallCenterThreadPoolExecutor.class);
	
	/** The employee cache singleton. */
	private EmployeesCache employeeCache;
	
	/**
	 * Instantiates a new call center thread pool executor.
	 *
	 * @param corePoolSize the core pool size
	 * @param maximumPoolSize the maximum pool size
	 * @param keepAliveTime the keep alive time
	 * @param unit the unit
	 * @param workQueue the work queue
	 * @param employeeCache the employee cache
	 */
	public CallCenterThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, EmployeesCache employeeCache) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		this.employeeCache = employeeCache;
		LOGGER.info("CallCenterThreadPoolExecutor has been instanced");
	}
	
    /* (non-Javadoc)
     * @see java.util.concurrent.ThreadPoolExecutor#afterExecute(java.lang.Runnable, java.lang.Throwable)
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        Call call = (Call) r;
        employeeCache.freeEmployee(call.getEmployee());
    }

}
