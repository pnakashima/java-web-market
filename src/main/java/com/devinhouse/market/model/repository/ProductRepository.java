package com.devinhouse.market.model.repository;

import com.devinhouse.market.model.persistence.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    public Product findByIdentifier(String identifier);

    @Query(value = "delete from product where identifier = :identifier", nativeQuery = true)
    public void deleteByIdentifier(String identifier);

}
