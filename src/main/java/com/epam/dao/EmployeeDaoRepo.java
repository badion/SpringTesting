package com.epam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.Employee;

public interface EmployeeDaoRepo extends JpaRepository<Employee, Long>{

}
