package com.devinhouse.market.model.repository;

import com.devinhouse.market.model.persistence.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    public Category findByName(String name);



}
