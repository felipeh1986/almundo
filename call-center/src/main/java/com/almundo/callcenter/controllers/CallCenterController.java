package com.almundo.callcenter.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.callcenter.business.Dispatcher;
import com.almundo.callcenter.dto.GeneralResponse;

@RestController
public class CallCenterController {

	private static final Logger LOGGER = Logger.getLogger(CallCenterController.class);
	
	@Autowired
	private Dispatcher dispatcher;
	
	@GetMapping("/almundo/callcenter/{phoneNumber}")
	public ResponseEntity<GeneralResponse<String>> makeCall(@PathVariable String phoneNumber){
		LOGGER.info("calling by " + phoneNumber);
		
		final String detail = dispatcher.dispatchCall(phoneNumber); 
		
		return buildResponse(detail);
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
		
		return new ResponseEntity<GeneralResponse<String>>(response, HttpStatus.OK);
	}
}
