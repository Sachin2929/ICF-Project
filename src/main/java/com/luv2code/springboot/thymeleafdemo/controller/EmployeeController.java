package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import com.mysql.cj.xdevapi.JsonString;

@Controller
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	// rest api to get all details
	@GetMapping("/getList")	
	public List<Employee> getEmployees()
	{
		List<Employee> employeeList = employeeService.findAll();
		return employeeList;
	}
	
	// rest api to delete row
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		
		// delete the employee
		employeeService.deleteById(id);
		
		// redirect to /employees/list
		return "redirect:index";
		
	}
	
	// rest api to find emp by id
	@GetMapping("/employeeList/{id}")
	public Employee getByIDNew(@PathVariable int id) {
		
		// get the employee from the service
		Employee theEmployee = employeeService.findById(id);		
		
		// send over to our form
		return theEmployee;			
	}
	
	//rest api to update emp
	@PostMapping("/save")	
	public Employee saveEmployee(@RequestBody Employee employee) {							
		
		employeeService.save(employee);
						
		return employee;
	}
	
	
	@PostMapping("/add")	
	public Employee addEmployee(@Validated @RequestBody Employee employee) {							
		
		employeeService.save(employee);
						
		return employee;
	}
	
}


















