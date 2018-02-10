package com.almundo.callcenter.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.almundo.callcenter.business.Dispatcher;
import com.almundo.callcenter.dto.Employee;
import com.almundo.callcenter.dto.GeneralResponse;
import com.almundo.callcenter.jpa.EmployeeRepository;
import com.almundo.callcenter.utils.DataProviderTest;

@RunWith(MockitoJUnitRunner.class)
public class CallCenterControllerTest {

	@InjectMocks
	private CallCenterController controller;
	
	@Mock
	private Dispatcher dispatcher;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	private Employee employ;
	
	@Before
	public void setup() {
		final List<Employee> employees = DataProviderTest.buildEmployees();
		employ = employees.get(0);
		when(employeeRepository.allEmployees()).thenReturn(employees);
		when(employeeRepository.save(employ)).thenReturn(employ);
	}
	
	@Test
	public void dispatch_call_from_controller_test() {
		final ResponseEntity<GeneralResponse<String>> result = controller.makeCall("3216038698");
		assertEquals(0, result.getBody().getCode());
	}
	
	@Test
	public void create_employee_controller_test() {
		final ResponseEntity<GeneralResponse<Employee>> result = controller.createEmployee(employ);
		assertNotNull(result.getBody().getDetail());
	}
	
	@Test
	public void get_employees_controller_test() {
		final ResponseEntity<GeneralResponse<List<Employee>>> result = controller.getEmployees();
		assertFalse(result.getBody().getDetail().isEmpty());
	}

}
