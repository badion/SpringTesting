package com.epam.jersey;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.validation.Valid;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.epam.dao.CustomerDao;
import com.epam.exceptions.NotFoundInDatabaseException;
import com.epam.integration.gateways.CountSumOfCustomers;
import com.epam.model.Customer;

@Component
@Path("/customer")
public class CustomerJerseyRest {

	@Autowired
	private CustomerDao customerDao;

	@GET
	@Path("/test")
	@Produces("text/plain;charset=UTF-8")
	public String print(@Context SecurityContext sc) {
		if (sc.isUserInRole("ROLE_ADMIN"))
			return "Messege for authorized user(admin(alex))";
		throw new SecurityException("User is unauthorized.");
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Long id) {
		try {
			Customer customer = customerDao.getCustomerById(id);
			if (customer != null) {
				return Response.status(200).entity(customer).build();
			}
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundInDatabaseException("not found in database");
		}
		return null;
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerDao.getAll();
		return customers;
	}

	@POST
	@Path("/register")
	@Produces(MediaType.TEXT_HTML)
	public Response saveCustomerPage(@Valid @FormParam("name") String name,
			@Valid @FormParam("age") int age,
			@Valid @FormParam("email") String email,
			@Valid @FormParam("password") String password) throws IOException,
			ServletException {

		Customer customer = new Customer();
		customer.setName(name);
		customer.setAge(age);
		customer.setEmail(email);
		customer.setPassword(password);
		if (customer != null) {
			customerDao.insert(customer);
		}

		return Response
				.status(200)
				.entity("add cust is called " + name + " age " + age
						+ " email " + email + " password " + password).build();
	}
}
