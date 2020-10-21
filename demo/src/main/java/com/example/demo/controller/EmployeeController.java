package com.example.demo.controller;

import java.util.List;

import javax.management.ServiceNotFoundException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Employee;
import com.example.demo.util.ServiceException;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1")
public class EmployeeController {
	@Qualifier("employeeTemplate")
	@Autowired
	private MongoTemplate employeeTemplate;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeTemplate.findAll(Employee.class);
	}

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") String employeeId) {
    	Query query = new Query(Criteria.where("_id").is(new ObjectId(employeeId)));
    	Employee e = employeeTemplate.findOne(query, Employee.class);
    	return ResponseEntity.ok(e);
    }

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeTemplate.save(employee);
	}

    @PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") String employeeId,
			@RequestBody Employee employeeDetails) throws Exception {
    	Query query = new Query(Criteria.where("_id").is(new ObjectId(employeeId)));
    	Employee e = employeeTemplate.findOne(query, Employee.class);
    	if (e == null) {
    		throw new ServiceException("k ton tai", HttpStatus.NOT_FOUND);
    		
    	}
    	e.setName(employeeDetails.getName());
    	e.setEmail(employeeDetails.getEmail());
    	e.setGender(employeeDetails.getGender());
    	e.setPosition(employeeDetails.getPosition());
		Employee updatedEmployee = employeeTemplate.save(e);
		return ResponseEntity.ok(updatedEmployee);
//    	return null;
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Boolean> deleteEmployee(@PathVariable(value = "id") String employeeId)
			throws ServiceNotFoundException {
		Employee e = new Employee();
		e.setId(new ObjectId(employeeId));
		return ResponseEntity.ok(employeeTemplate.remove(e).getDeletedCount() > 0);
	}
}