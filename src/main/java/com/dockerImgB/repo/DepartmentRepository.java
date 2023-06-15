package com.dockerImgB.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dockerImgB.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	Department getDepartmentById(int id);

}
