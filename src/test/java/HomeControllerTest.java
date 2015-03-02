import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import javassist.bytecode.Descriptor.Iterator;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.controller.HomeController;
import com.epam.dao.CustomerDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:customer/spring-customer.xml",
		"classpath:database/splitterDS.xml" })
public class HomeControllerTest {

	@Autowired
	private CustomerDao customerDao;

	@Test
	public void checkCustomerDao() {
		Assert.assertNotNull(customerDao);
	}


	@Test
	public void showHomePage() {
		Assert.assertEquals("hello", new HomeController().showHomePage());
	}

	@Test
	public void testAdminPage() {
		Assert.assertNotNull(new HomeController().adminPage());
	}

	@Test
	public void mockIterator() {
		Iterator i = mock(Iterator.class);
		when(i.next()).thenReturn(11).thenReturn(12);

		String result = i.next() + " " + i.next();

		Assert.assertEquals("11 12", result);
	}
}
