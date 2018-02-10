package com.almundo.callcenter.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.almundo.callcenter.dto.Employee;

/**
 * The Class JpaProxyEmployeeRepository is a respository responsible to makes the persistence and convert operations.
 */
@Repository
public class JpaProxyEmployeeRepository implements EmployeeRepository {

	/** The employee persistence repository. */
	@Autowired
	private EmployeeJpaRepository employeeRepository;
	
	/* (non-Javadoc)
	 * @see com.almundo.callcenter.business.EmployeeRepository#save(com.almundo.callcenter.dto.Employee)
	 */
	@Override
	public Employee save(Employee employee) {
		final EmployeeEntity entity = buildEmployeeEntity(employee);
		final EmployeeEntity entitySaved = employeeRepository.save(entity);
		return buildEmployeeDto(entitySaved);
	}

	/* (non-Javadoc)
	 * @see com.almundo.callcenter.business.EmployeeRepository#allEmployees()
	 */
	@Override
	public List<Employee> allEmployees() {
		final List<EmployeeEntity> entities = employeeRepository.findAll();
		final List<Employee> employees = new ArrayList<>();
		
		entities.forEach(e -> employees.add(buildEmployeeDto(e)));
		
		return employees;
	}

	/**
	 * Builds the employee entity.
	 *
	 * @param emp the employee DTO
	 * @return the employee entity
	 */
	private EmployeeEntity buildEmployeeEntity(final Employee emp) {
		final EmployeeEntity entity = new EmployeeEntity();
		entity.setId(emp.getId());
		entity.setName(emp.getName());
		entity.setType(emp.getType());
		entity.setChiefId(emp.getChief_id());
		
		return entity;
	}
	
	/**
	 * Builds the employee DTO.
	 *
	 * @param emp the employee entity
	 * @return the employee
	 */
	private Employee buildEmployeeDto(final EmployeeEntity emp) {
		final Employee dto = new Employee();
		dto.setId(emp.getId());
		dto.setName(emp.getName());
		dto.setType(emp.getType());
		dto.setChief_id(emp.getChiefId());
		
		return dto;
	}

}
