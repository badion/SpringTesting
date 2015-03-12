package com.epam.aspect;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.codehaus.jettison.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.annotation.Payload;

import com.epam.dao.CustomerDao;
import com.epam.integration.gateways.CountSumOfCustomers;
import com.epam.integration.gateways.CustomerById;
import com.epam.integration.gateways.CustomersObject;
import com.epam.integration.gateways.MailGateway;
import com.epam.model.Customer;

@Aspect
public class InjectionAspect {

	private static final String META_INF_INTEGRATION_SAVE_DATA_FROM_REGISTER_USER_XML = "/META-INF/integration/save-data-from-register-user.xml";
	private static final String META_INF_INTEGRATION_SEND_MAIL_WHEN_REGISTRATION_XML = "/META-INF/integration/send-mail-when-registration.xml";
	private static final String META_INF_INTEGRATION_SAVE_JSON_FROM_GET_BY_ID_XML = "/META-INF/integration/save-json-from-get-by-id.xml";
	private static final String META_INF_INTEGRATION_PARSE_AND_REVERSE_JSON_XML = "/META-INF/integration/parse-and-reverse-json.xml";
	private static final String META_INF_INTEGRATION_HTTP_OUTBOUND_CONFIG_XML = "/META-INF/integration/http-outbound-config.xml";
	private static final String META_INF_INTEGRATION_SPRING_INTEGRATION_CONTEXT_XML = "/META-INF/integration/spring-integration-context.xml";

	private CustomerDao customerDao;

	private ApplicationContext context;

	@Before("execution(* com.epam.dao.CustomerDao.getAll())")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("logBefore() is running");
		System.out.println(joinPoint.getSignature().getName());
		System.out.println("********");
	}

	@Pointcut("execution(* com.epam.dao.CustomerDao.insert())")
	private void businessService() {
		System.out.println("Insert method");
	}

	@Before("execution(* com.epam.jersey.CustomerJerseyRest.getAllCustomers(..))")
	public void customersCounter(JoinPoint joinPoint) {
		context = new ClassPathXmlApplicationContext(
				META_INF_INTEGRATION_SPRING_INTEGRATION_CONTEXT_XML);
		CountSumOfCustomers amount = context.getBean("countSum",
				CountSumOfCustomers.class);
		amount.amountOfCustomers(customerDao.getAll().size());
	}

	@Before("execution(* com.epam.jersey.CustomerJerseyRest.getAllCustomers(..))")
	public void enrichCustomerList(JoinPoint joinPoint) throws JSONException {
		context = new ClassPathXmlApplicationContext(
				META_INF_INTEGRATION_PARSE_AND_REVERSE_JSON_XML);
	}

	@Before("execution(* com.epam.jersey.CustomerJerseyRest.getAllCustomers(..))")
	public void customersJson(JoinPoint joinPoint) throws JSONException {
		context = new ClassPathXmlApplicationContext(
				META_INF_INTEGRATION_HTTP_OUTBOUND_CONFIG_XML);
		CustomersObject jsonOfCustumers = context.getBean("requestGateway",
				CustomersObject.class);

		List<Customer> customers = new ArrayList<Customer>();
		for (Customer cust : customerDao.getAll()) {
			customers.add(cust);
		}
		jsonOfCustumers.saveObject(customers);
	}

	@Before("execution(* com.epam.jersey.CustomerJerseyRest.findById(*))"
			+ " && args(id)")
	public void customerGetById(JoinPoint called, Long id) throws JSONException {
		context = new ClassPathXmlApplicationContext(
				META_INF_INTEGRATION_SAVE_JSON_FROM_GET_BY_ID_XML);

		com.epam.integration.gateways.CustomerById customerById = context.getBean(
				"customerById", CustomerById.class);
		customerById.saveObject(customerDao.getCustomerById(id));

	}

	@Before("execution(* com.epam.jersey.CustomerJerseyRest.saveCustomerPage(..))"
			+ " && args(name, age, email, password)")
	@Payload("#args")
	public void saveDataFromRegisterUser(JoinPoint joinPoint, String name, Integer age, String email, String password) {
		context = new ClassPathXmlApplicationContext(
				META_INF_INTEGRATION_SAVE_DATA_FROM_REGISTER_USER_XML);
		context.getBean("recieveParams");
		MailGateway sendToXmlParameters = context.getBean("recieveParams",
				MailGateway.class);
		sendToXmlParameters.sendMail(name, age, email, password);
	}
	
	@After("execution(* com.epam.jersey.CustomerJerseyRest.saveCustomerPage(..))"
			+ " && args(name, age, email, password)")
	public void sendEmail(JoinPoint joinPoint, String name, Integer age, String email, String password) {
		context = new ClassPathXmlApplicationContext(
				META_INF_INTEGRATION_SEND_MAIL_WHEN_REGISTRATION_XML);
	}

	@Inject
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
}
