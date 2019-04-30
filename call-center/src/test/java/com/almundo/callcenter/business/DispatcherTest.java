package com.almundo.callcenter.business;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.almundo.callcenter.dto.Employee;
import com.almundo.callcenter.jpa.EmployeeRepository;
import com.almundo.callcenter.utils.DataProviderTest;

@RunWith(MockitoJUnitRunner.class)
public class DispatcherTest {

	@InjectMocks
	private Dispatcher dispatcher;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Mock
	private EmployeesCache employeesCache;
	
	@Mock
	private CallCenterThreadPoolExecutor executor;
	
	@Before
	public void setup() {
		final List<Employee> employees = DataProviderTest.buildEmployees();
		EmployeesCache.getInstance(employees);
		//when(employeeRepository.allEmployees()).thenReturn(employees);
		when(employeesCache.getEmployeeAvailable()).thenReturn(employees.get(0));
	}
	
	@Test()
	public void dispatch_call_test() {
		final String result = dispatcher.dispatchCall(DataProviderTest.generateRandomNumber().toString(), 0);
		assertNull(result);
	}
	
	

}
