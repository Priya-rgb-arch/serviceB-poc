package com.dockerImgB.controller;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dockerImgB.entity.Department;
import com.dockerImgB.service.DepartmentService;

@RestController
@RequestMapping(value = "/api/v1")
public class DepartmentController {

	private static final Logger logger = Logger.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentService;

	@PostMapping(path = "/saveDepartment")
	public ResponseEntity<Department> saveEmployee(@RequestBody Department department) {
		logger.info("Entring in saveEmployee() of DepartmentController");
		Department dept = null;
		try {
			dept = departmentService.saveDepartment(department);
			logger.info("Existing from saveEmployee() of EmployeeController");
			return ResponseEntity.of(Optional.of(dept));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(path = "/getDepartments")
	public ResponseEntity<List<Department>> getDepartments() {
		logger.info("Entring in getDepartments() of DepartmentController");
		List<Department> dept = departmentService.getDepartments();
		if (dept.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		logger.info("Existing from getDepartments() of DepartmentController");
		return ResponseEntity.of(Optional.of(dept));
	}
}
