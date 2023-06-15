package com.dockerImgB.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dockerImgB.entity.Employee;

@Service
public interface EmployeeService {
	List<Employee> getEmployees();

	Employee getemployeeById(int id);

	Employee saveEmployee(int id, Employee employee);

	void deleteEmployee(int id);

	Employee updateEmployee(Employee employee, int id);
}
