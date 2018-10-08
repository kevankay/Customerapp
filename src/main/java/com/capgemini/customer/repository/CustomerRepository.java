package com.capgemini.customer.repository;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.customer.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}
