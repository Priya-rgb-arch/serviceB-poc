package com.dockerImgB.controller;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dockerImgB.entity.Employee;
import com.dockerImgB.exception.EmployeeNotFoundBYIDException;
import com.dockerImgB.service.EmployeeService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1")
public class EmployeeController {

	private static final Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@ApiOperation(value = "Get List Of Employee", notes = "Employee must exist")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "List<Employee> Object"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@GetMapping(path = "/getEmployees")
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token"),
		})
	public ResponseEntity<List<Employee>> getEmployees() {
		logger.info("Entring in getEmployees() of EmployeeController");
		List<Employee> emps = employeeService.getEmployees();
		if (emps.size() <= 0) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		logger.info("Existing from getEmployees() of EmployeeController");
		return ResponseEntity.of(Optional.of(emps));
	}

	@ApiOperation(value = "Get Employee By ID", notes = "Employee must exist")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee Object"),
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@GetMapping(path = "/getEmployee/{employeeId}")
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token"),
		})
	public ResponseEntity<Employee> getemployee(@PathVariable("employeeId") int id) {
		logger.info("Entring in getemployee() of EmployeeController");
		Employee emp = employeeService.getemployeeById(id);
		if (emp == null) {
			// return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			throw new EmployeeNotFoundBYIDException("Employee", "ID", id);
		}
		logger.info("Existing from getemployee() of EmployeeController");
		return ResponseEntity.of(Optional.of(emp));
	}

	@ApiOperation(value = "Save New Employee Records", notes = "Employee must Not exist")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Employee created"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "409", description = "Employee already exists"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@PostMapping(path = "/saveEmployee")
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token"),
		})
	public ResponseEntity<Employee> saveEmployee(@RequestParam(name = "departmentId") int id,
			@RequestBody Employee employee) {
		logger.info("Entring in saveEmployee() of EmployeeController");
		Employee emp = null;
		try {
			emp = employeeService.saveEmployee(id, employee);
			logger.info("Existing from saveEmployee() of EmployeeController");
			return ResponseEntity.of(Optional.of(emp));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "Delete Employee By ID", notes = "Employee must exist")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@DeleteMapping(path = "/deleteEmployee/{employeeId}")
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token"),
		})
	public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") int id) {
		logger.info("Entring in deleteEmployee() of EmployeeController");
		try {
			employeeService.deleteEmployee(id);
			logger.info("Existing from deleteEmployee() of EmployeeController");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "Update Employee Details By ID", notes = "Employee must exist")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@PutMapping(path = "/updateEmployee/{employeeId}")
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token"),
		})
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("employeeId") int id) {
		logger.info("Entring in updateEmployee() of EmployeeController");
		Employee emp = null;
		try {
			emp = employeeService.updateEmployee(employee, id);
			logger.info("Existing from updateEmployee() of EmployeeController");
			return ResponseEntity.ok().body(emp);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}