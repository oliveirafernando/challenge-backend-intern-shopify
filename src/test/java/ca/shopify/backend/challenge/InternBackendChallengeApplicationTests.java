package ca.shopify.backend.challenge;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InternBackendChallengeApplicationTests {
	
	@Before
	public void setUp() throws Exception{
		
	}

	@Test
	public void contextLoads() {
		assertEquals(1,  1);
	}

}
