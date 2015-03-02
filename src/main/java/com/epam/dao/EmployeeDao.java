package com.epam.dao;

import java.util.List;

import com.epam.model.Employee;

public interface EmployeeDao {

	public Employee getEmpById(int id);
	
	public List<Employee> getAll();
	
	public void insert(Employee emp);

	public void delete(Employee empById);
	
	public Employee getEmpByName(String name);
}
