package com.epam.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.epam.dao.EmployeeDao;
import com.epam.model.Customer;
import com.epam.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private static final String SELECT_FROM_EMPLOYEE_WHERE_ID = "SELECT * FROM EMPLOYEE WHERE ID=?";

	private static final String ID2 = "id";

	private static final String AGE = "age";

	private static final String NAME = "name";

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Employee getEmpById(int id) {
		return jdbcTemplate.queryForObject(SELECT_FROM_EMPLOYEE_WHERE_ID,
				new ParameterizedRowMapper<Employee>() {
					public Employee mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Employee employee = new Employee(rs.getInt(ID2), rs
								.getString(NAME), rs.getInt(AGE));
						return employee;
					}
				}, id);
	}

	public EmployeeDaoImpl() {

	}

	@Override
	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<Employee>();
		for (Map<String, Object> map : jdbcTemplate
				.queryForList("SELECT * FROM employee")) {
			Employee employee = new Employee((Integer) map.get("id"),
					(String) map.get("name"), (Integer) map.get("age"));
			employees.add(employee);
		}
		return employees;
	}

	@Override
	public void insert(Employee emp) {
		jdbcTemplate.update("INSERT INTO Employee (NAME, AGE) VALUES (?, ?)",
				emp.getName(), emp.getAge());
	}

	@Override
	public void delete(Employee empById) {
		jdbcTemplate.update("delete from Employee where id=?", empById.getId());
		System.out.println("deleted");
	}

	@Override
	public Employee getEmpByName(String name) {
		return jdbcTemplate.queryForObject(
				"select * from employee where name=?",
				new ParameterizedRowMapper<Employee>() {
					public Employee mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Employee employee = new Employee(rs.getInt(ID2), rs
								.getString(NAME), rs.getInt(AGE));
						return employee;
					}
				}, name);
	}
}
