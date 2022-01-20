package com.devinhouse.market.model.repository;

import com.devinhouse.market.model.persistence.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
