package com.devinhouse.market.model.repository;

import com.devinhouse.market.model.persistence.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {



}
