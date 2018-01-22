package com.test.java;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.conctrol.LoginController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
@WebAppConfiguration
public class appControlerTest
{
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception
	{
		mvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
	}

	@Test
	public void getHello() throws Exception
	{
		String output1=mvc.perform(MockMvcRequestBuilders.get("/app/hello").param("uName", "zhangsan").param("age", "28")).andReturn().getResponse().getContentAsString();
		System.out.println("get request:"+output1);
		String output2=mvc.perform(MockMvcRequestBuilders.post("/app/hello").param("uName", "zhangsan").param("age", "28")).andReturn().getResponse().getContentAsString();
		System.out.println("post request:"+output2);
		/*mvc.perform(MockMvcRequestBuilders.get("/app/hello").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();*/
		
	}
}
