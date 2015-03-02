package com.epam.scheduling;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.epam.model.Customer;

@Component
@Path("/send")
public class SendMailRestController {

	private NumberOfCustomers numberOfCustomers;

	private List<Customer> customers;

	public List<Customer> customersFromJson() {
		RestTemplate restTemplate = new RestTemplate();
		setCustomers(Arrays.asList(restTemplate.getForObject(
				"http://localhost:8080/SpringInActionPart6/rest/customer/all",
				Customer[].class)));
		System.out.println(Arrays.asList(restTemplate.getForObject(
				"http://localhost:8080/SpringInActionPart6/rest/customer/all",
				Customer[].class)));
		return customers;
	}

	@Inject
	public void setMailSender(NumberOfCustomers numberOfCustomers) {
		this.numberOfCustomers = numberOfCustomers;
	}

	@GET
	@Path("/mail")
	public Response sendMail() {
		numberOfCustomers.sendEmail("badion926@gmail.com",
				"vitalii_badion@epam.com", customersFromJson());
		return Response.status(200).entity("Email successfully sent").build();
	}

	@Scheduled(fixedDelay = 50000)
	public void demoMethod() {
		System.out
				.println("Method executed at every 5 seconds. Current time is :: "
						+ new Date());
	}

	/**
	 * @return the customers
	 */
	public List<Customer> getCustomers() {
		return customers;
	}

	/**
	 * @param customers
	 *            the customers to set
	 */
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}
