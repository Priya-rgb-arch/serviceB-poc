package com.dockerImgB.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dockerImgB.entity.Department;
import com.dockerImgB.entity.Employee;
import com.dockerImgB.repo.DepartmentRepository;
import com.dockerImgB.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Employee> getEmployees() {
		List<Employee> emps = employeeRepository.findAll();
		return emps;
	}

	@Override
	public Employee getemployeeById(int id) {
		Employee employee = employeeRepository.getEmployeeById(id);
		return employee;
	}

	@Override
	public Employee saveEmployee(int id, Employee employee) {
		Department department = departmentRepository.getDepartmentById(id);
		Employee emp = null;
		if (employee != null) {
			emp = new Employee();
			emp.setFirstName(employee.getFirstName());
			emp.setEmpSalary(employee.getEmpSalary());
			emp.setStatus("Active");
			emp.setDepartment(department);
			emp = employeeRepository.save(emp);
		}
		return emp;
	}

	@Override
	public void deleteEmployee(int id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			Employee employee2 = employee.get();
			employee2.setStatus("Inactive");
			employeeRepository.save(employee2);
		}
	}

	@Override
	public Employee updateEmployee(Employee employee, int id) {
		Employee employee2 = getemployeeById(id);
		if (employee2 != null) {
			employee2.setFirstName(employee.getFirstName());
			employee2.setEmpSalary(employee.getEmpSalary());
			employee2 = employeeRepository.save(employee2);
		}
		return employee2;
	}

}
