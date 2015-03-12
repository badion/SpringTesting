package com.epam.jerseytest;

import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.submit;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.epam.dao.CustomerDao;
import com.epam.model.Customer;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.grizzly2.web.GrizzlyWebTestContainerFactory;
public class CustomerJerseyTest extends JerseyTest {

	@Mock
	private CustomerDao customerDao;

	private MockMvc mockMvc;

	@Autowired
	private CustomerDao customerDaoReal;

	@Override
	public TestContainerFactory getTestContainerFactory() {
		return new GrizzlyWebTestContainerFactory();
	}

	public CustomerJerseyTest() throws Exception {
		super("com.epam.jersey");
	}

	@Override
	protected AppDescriptor configure() {
		return super.configure();
	}

	@Before
	public void setup() {
		customerDao = mock(CustomerDao.class);
	    setBaseUrl("http://localhost:8080/SpringInActionPart6/");
	}

	@Test
	public void testFindAll() {
		List<Customer> all = new ArrayList<Customer>();
		all.add(new Customer(1, "vitalii", 12, "badion@bigmir.net", "password", ""));
		all.add(new Customer(2, "alex", 12, "badion@bigmir.net", "password", ""));
		when(customerDao.getAll()).thenReturn(all);
		List<Customer> result1 = customerDao.getAll();
		List<Customer> result2 = customerDaoReal.getAll();
		Assert.assertEquals(result1.toString(), result2.toString());
	}

	@Test
	public void checkAllCustomers() {
		RestTemplate restTemplate = new RestTemplate();
		List<Customer> customers = Arrays.asList(restTemplate.getForObject(
				"http://localhost:8080/SpringInActionPart6/rest/customer/all",
				Customer[].class));
		List<Customer> fromDB = customerDaoReal.getAll();
		Assert.assertEquals(fromDB.toString(), customers.toString());
	}

	
	  @Test
	    public void testLogin() {
	        beginAt("/registration");
	        setTextField("name", "vitalii");
	        setTextField("password", "test123");
	        submit();
	        assertTitleEquals("Welcome, test!");
	    }

	@Test
	public void findByIdTest() throws JSONException {
		WebResource webResource = client().resource("http://localhost:8080/");
		JSONObject json = webResource.path(
				"/SpringInActionPart6/rest/customer/1").get(JSONObject.class);

		Assert.assertEquals(1, json.get("custId"));
		Assert.assertEquals("badion@bigmir.net", json.get("email"));
		Assert.assertEquals(12, json.get("age"));
		Assert.assertEquals("vitalii", json.get("name"));
		Assert.assertEquals("password", json.get("password"));
	}
}