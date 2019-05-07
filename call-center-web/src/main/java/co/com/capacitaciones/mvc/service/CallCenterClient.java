package co.com.capacitaciones.mvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import co.com.capacitaciones.mvc.dtos.Employee;
import co.com.capacitaciones.mvc.dtos.GeneralResponse;

@Scope(value = "session")
@Component(value = "callCenterClient")
public class CallCenterClient {

    private RestTemplate restTemplate;
    
    private List<Employee> employees;
    
    private void getAllEmployees() {
    	restTemplate = new RestTemplate();
    	@SuppressWarnings("unchecked")
		final GeneralResponse<List<Employee>> response = restTemplate.getForObject("http://localhost:8762/call-center/almundo/callcenter/getEmployees", GeneralResponse.class);
    	employees = response.getDetail();
    }

    @HystrixCommand(fallbackMethod = "defaultGetEmployees")
    public List<Employee> getEmployees() {
		getAllEmployees();
		if(Objects.isNull(employees)) {
			employees = new ArrayList<Employee>();
		}
		return employees;
	}
    
    @SuppressWarnings("unused")
	private List<Employee> defaultGetEmployees() {
    	System.out.println("Se ha presentado un error");
    	return new ArrayList<Employee>();
    }

   
}
