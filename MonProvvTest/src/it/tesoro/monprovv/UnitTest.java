package it.tesoro.monprovv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" , "classpath:applicationContext.xml" })
public class UnitTest {

	
	@Test
	public void test() {
		System.out.println("OK");
	}

}
