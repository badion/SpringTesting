
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.epam.jerseytest.CustomerJerseyTest;

@RunWith(Suite.class)
@SuiteClasses({ HomeControllerTest.class, CustomerJerseyTest.class })
public class AllTests {

}
