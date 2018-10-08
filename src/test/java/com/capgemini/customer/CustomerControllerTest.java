package com.capgemini.customer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.customer.controller.CustomerController;
import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerControllerTest {

	MockMvc mockMvc;

	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testAuthenticateCustomer() throws Exception {
		String content = "{\"customerId\": 1234," + "  \"customerPassword\": \"kee\"}";
		Customer customer = new Customer(1234, "Keerthana", "kee@gmail.com", "GNT", "kee");
		when(customerService.authentication(Mockito.isA(Customer.class))).thenReturn(customer);
		mockMvc.perform(post("/authenticate").content(content).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andExpect(jsonPath("$.customerId").exists())
				.andExpect(jsonPath("$.customerId").value("1234"));
		verify(customerService).authentication(Mockito.isA(Customer.class));
	}

	@Test
	public void testEditCustomer() throws Exception {
		String content = "{\"customerId\": 1234," + "  \"customerName\": \"Keerthana\","
				+ "  \"customerEmail\": \"kee@gmail.com\"," + "  \"customerAddress\": \"GNT\","
				+ "  \"customerPassword\": \"kee\"" + "}";
		Customer customer = new Customer(1234, "Keerthana", "kee@gmail.com", "GNT", "kee");
		when(customerService.editCustomer(Mockito.isA(Customer.class))).thenReturn(customer);
		mockMvc.perform(put("/customer").content(content).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(jsonPath("$.customerId").exists())
				.andExpect(jsonPath("$.customerAddress").value("GNT"));
	}

	@Test
	public void testGetCustomer() throws Exception {
		String content = "{" + "  \"customerId\": 1234," + "  \"customerName\": \"Keerthana\","
				+ "  \"customerEmail\": \"kee@gmail.com\"," + "  \"customerAddress\": \"GNT\","
				+ "  \"customerPassword\": \"kee\"" + "}";
		Customer customer = new Customer(1234, "Keerthana", "kee@gmail.com", "GNT", "kee");
		when(customerService.findCustomerById(1234)).thenReturn(customer);
		mockMvc.perform(
				get("/customer/1234").contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(jsonPath("$.customerId").exists())
				.andExpect(jsonPath("$.customerName").value("Keerthana"));
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		Customer customer = new Customer(1234, "Keerthana", "kee@gmail.com", "GNT", "kee");
		when(customerService.findCustomerById(1234)).thenReturn(customer);
		mockMvc.perform(delete("/customer/1234").contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

}