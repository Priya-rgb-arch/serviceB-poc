package com.dockerImgB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dockerImgB.entity.Department;
import com.dockerImgB.repo.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartment(Department department) {
		Department dept = null;
		if (department != null) {
			dept = new Department();
			dept.setDepartmentName(department.getDepartmentName());
			dept.setDepartmentLocation(department.getDepartmentLocation());
			dept.setStatus("Active");
			dept = departmentRepository.save(dept);
		}
		return dept;
	}

	@Override
	public List<Department> getDepartments() {
		List<Department> dept = departmentRepository.findAll();
		return dept;
	}

}
