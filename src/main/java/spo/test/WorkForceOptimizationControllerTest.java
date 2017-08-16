package spo.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import spo.domain.Room;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WorkForceOptimizationControllerTest {
	@Autowired
    private MockMvc mockMvc;
	@Before
	public void setUp() throws Exception {
		Room room = new Room();
		int[] capacities = {30,22,43};
		
		room.setCapacity(capacities);
		room.setJunior(6);
		room.setSenior(10);
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
