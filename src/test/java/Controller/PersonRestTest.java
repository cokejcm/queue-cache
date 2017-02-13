package Controller;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.app.configuration.JerseyConfig;
import com.demo.app.controller.PersonRest;
import com.demo.app.service.PersonService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonRestTest extends JerseyTest {

	@InjectMocks
	private PersonRest restResource;
	@Mock
	private PersonService personService;

	@Override
	protected TestContainerFactory getTestContainerFactory() {
		return new GrizzlyWebTestContainerFactory();
	}

	@Override
	protected DeploymentContext configureDeployment() {
		MockitoAnnotations.initMocks(this);
		return ServletDeploymentContext.forServlet(new ServletContainer(new JerseyConfig().register(restResource))).build();
	}

	@Test
	public void testGenerate2FakePeople() {
		Response response = target("person/generateFake/2").request().put(Entity.entity(new Integer(0), MediaType.TEXT_PLAIN));
		Assert.assertEquals(response.readEntity(String.class), "2 Items of type Person inserted");
	}
}
