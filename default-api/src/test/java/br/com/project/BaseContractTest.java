package br.com.project;

import br.com.project.resource.CustomerController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public abstract class BaseContractTest {

    @Autowired
    private CustomerController customerController;

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(customerController);
    }
}
