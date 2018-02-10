package com.almundo.callcenter.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.callcenter.business.Dispatcher;
import com.almundo.callcenter.dto.Employee;
import com.almundo.callcenter.dto.GeneralResponse;
import com.almundo.callcenter.jpa.EmployeeRepository;

@RestController
@ComponentScan("com.almundo.callcenter.jpa")
public class CallCenterController {

	private static final Logger LOGGER = Logger.getLogger(CallCenterController.class);
	
	@Autowired
	private Dispatcher dispatcher;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/almundo/callcenter/{phoneNumber}")
	public ResponseEntity<GeneralResponse<String>> makeCall(@PathVariable String phoneNumber){
		LOGGER.info("calling by " + phoneNumber);
		
		final String detail = dispatcher.dispatchCall(phoneNumber, 0); 
		
		return buildResponse(detail);
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/almundo/callcenter/createEmployee")
	public ResponseEntity<GeneralResponse<Employee>> createEmployee(@RequestBody final Employee request){
		LOGGER.info("Creating employee ");
		
		final Employee employee = employeeRepository.save(request); 
		
		final GeneralResponse<Employee> response = new GeneralResponse<>();
		
		response.setCode(0);
		response.setDescription("Operation success");
		response.setDetail(employee);
		
		return new ResponseEntity<GeneralResponse<Employee>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/almundo/callcenter/getEmployees")
	public ResponseEntity<GeneralResponse<List<Employee>>> getEmployees(){
		LOGGER.info("Getting employees from database ...");
		final GeneralResponse<List<Employee>> response = new GeneralResponse<>();
		
		final List<Employee> detail = employeeRepository.allEmployees();
		
		response.setCode(0);
		response.setDescription("Operation success");
		response.setDetail(detail);
		
		return new ResponseEntity<GeneralResponse<List<Employee>>>(response, HttpStatus.OK);
	}
	
	private ResponseEntity<GeneralResponse<String>> buildResponse(final String detail){
		GeneralResponse<String> response = new GeneralResponse<String>();
		
		if(detail != null) {
			response.setCode(1);
			response.setDescription("Operation failed");
			response.setDetail(detail);
			return new ResponseEntity<GeneralResponse<String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setCode(0);
		response.setDescription("Operation success");
		response.setDetail("The call has been respond");
		
		return new ResponseEntity<GeneralResponse<String>>(response, HttpStatus.OK);
	}
}
