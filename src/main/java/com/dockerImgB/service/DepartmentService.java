package com.dockerImgB.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dockerImgB.entity.Department;

@Service
public interface DepartmentService {

	Department saveDepartment(Department department);

	List<Department> getDepartments();

}
