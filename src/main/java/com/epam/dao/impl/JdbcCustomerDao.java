package com.epam.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.epam.dao.CustomerDao;
import com.epam.model.Customer;
import com.epam.model.Employee;

public class JdbcCustomerDao implements CustomerDao {

	private static final String ACCESS_TOKEN = "access_token";

	private static final String PASSWORD = "password";

	private static final String EMAIL = "email";

	private static final String CUST_ID = "CUST_ID";

	private static final String AGE = "AGE";

	private static final String NAME = "NAME";

	private static final String SELECT_FROM_CUSTOMER_WHERE_CUST_ID = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";

	private static final String INSERT_INTO_CUSTOMER_CUST_ID_NAME_AGE_VALUES = "INSERT INTO CUSTOMER (NAME, AGE, EMAIL, PASSWORD, access_token) VALUES (?, ?, ?, ?, ?)";

	private static final String SELECT_ALL_FROM_CUSTOMER = "SELECT * FROM CUSTOMER";

	private static final String SELECT_CUSTOMER_BY_ACCESS_TOKEN = "SELECT * FROM CUSTOMER WHERE access_token = ?";

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	private TransactionTemplate transactionTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(final Customer customer) {

		getTransactionTemplate().execute(new TransactionCallback<Void>() {
			@Override
			public Void doInTransaction(TransactionStatus txStatus) {
				try {
					jdbcTemplate.update(
							INSERT_INTO_CUSTOMER_CUST_ID_NAME_AGE_VALUES,
							customer.getName(), customer.getAge(),
							customer.getEmail(), customer.getPassword(),
							customer.getFacebookId());
				} catch (RuntimeException e) {
					txStatus.setRollbackOnly();
					throw e;
				}
				return null;
			}
		});

	}

	public Customer findByCustomerId(int custId) {
		return jdbcTemplate.queryForObject(SELECT_FROM_CUSTOMER_WHERE_CUST_ID,
				new ParameterizedRowMapper<Customer>() {
					public Customer mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Customer customer = new Customer(rs.getInt(CUST_ID), rs
								.getString(NAME), rs.getInt(AGE), rs
								.getString(EMAIL), rs.getString(PASSWORD), rs
								.getString(ACCESS_TOKEN));
						return customer;
					}
				}, custId);
	}

	@Override
	public List<Customer> getAll() {
		List<Customer> customers = new ArrayList<Customer>();
		for (Map<String, Object> map : jdbcTemplate
				.queryForList(SELECT_ALL_FROM_CUSTOMER)) {
			Customer customer = new Customer((Integer) map.get(CUST_ID),
					(String) map.get(NAME), (Integer) map.get(AGE),
					(String) map.get(EMAIL), (String) map.get(PASSWORD),
					(String) map.get(ACCESS_TOKEN));
			customers.add(customer);
		}
		return customers;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public Customer getCustomerById(Long id) {
		return jdbcTemplate.queryForObject(
				"SELECT * FROM CUSTOMER WHERE cust_id=?",
				new ParameterizedRowMapper<Customer>() {
					public Customer mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Customer customer = new Customer(rs.getInt("CUST_ID"),
								rs.getString(NAME), rs.getInt(AGE), rs
										.getString(EMAIL), rs
										.getString(PASSWORD), rs
										.getString(ACCESS_TOKEN));
						return customer;
					}
				}, id);
	}

	@Override
	public Customer getCustomerByFacebookId(String facebookId) {
		return jdbcTemplate.queryForObject(SELECT_CUSTOMER_BY_ACCESS_TOKEN,
				new ParameterizedRowMapper<Customer>() {
					public Customer mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Customer customer = new Customer(rs.getInt(CUST_ID), rs
								.getString(NAME), rs.getInt(AGE), rs
								.getString(EMAIL), rs.getString(PASSWORD), rs
								.getString(ACCESS_TOKEN));
						return customer;
					}
				}, facebookId);
	}


}