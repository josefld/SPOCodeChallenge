package spo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WorkForceOptimizationControllerTest {
	@Autowired
    private MockMvc mockMvc;
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidParametersAndOptimization() throws Exception {
		 this.mockMvc.perform(get("/workForceOptimization?capacity=24,28&junior=6&senior=11")).andDo(print()).andExpect(status().isOk())
		 .andExpect(jsonPath("$.[0].Junior").value(1))
		 .andExpect(jsonPath("$.[0].Senior").value(2))
		 .andExpect(jsonPath("$.[1].Junior").value(1))
		 .andExpect(jsonPath("$.[1].Senior").value(2));	
	}
	
	@Test
	public void testInvalidParameters() throws Exception {
		 this.mockMvc.perform(get("/workForceOptimization?capacity=AAA")).andDo(print()).andExpect(status().is4xxClientError());	
	}
	
	@Test
	public void testRequiredParameters() throws Exception {
		 this.mockMvc.perform(get("/workForceOptimization?capacity=24")).andDo(print()).andExpect(status().is4xxClientError());
	}

}
