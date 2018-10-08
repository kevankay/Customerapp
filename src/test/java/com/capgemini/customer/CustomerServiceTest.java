package com.capgemini.customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.repository.CustomerRepository;
import com.capgemini.customer.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	CustomerServiceImpl customerService;

	@org.junit.Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAuthenticateCustomer() throws Exception {
		Customer customer = new Customer(1234, "Keerthana", "kee@gmail.com", "GNT", "kee");
		when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
		assertEquals(customerService.authentication(customer), customer);
	}

	@Test
	public void testEditCustomer() throws Exception {
		Customer customer = new Customer(1234, "Keerthana", "kee@gmail.com", "GNT", "kee");
		when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customerService.editCustomer(customer), customer);

	}

	@Test
	public void testGetCustomer() throws Exception {
		Customer customer = new Customer(1234, "Keerthana", "kee@gmail.com", "GNT", "kee");
		when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
		assertEquals(customerService.findCustomerById(1234), customer);
	}

	@Test
	public void testGetAllCustomer() throws Exception {
		List<Customer> customers = new ArrayList<>();
		Customer customer = new Customer(1234, "Keerthana", "kee@gmail.com", "GNT", "kee");
		customers.add(customer);
		when(customerRepository.findAll()).thenReturn(customers);
		assertEquals(customerService.findAllCustomers(), customers);
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		Customer customer = new Customer(1234, "Keerthana", "kee@gmail.com", "GNT", "kee");
		when(customerRepository.findById(1234)).thenReturn(Optional.of(customer));
		customerService.deleteCustomer(customer);
		verify(customerRepository, times(1)).delete(customer);
	}

	@Test
	public void testAddCustomer() throws Exception {
		Customer customer = new Customer(1234, "Keerthana", "kee@gmail.com", "GNT", "kee");
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customerService.addCustomer(customer), customer);
	}

}
