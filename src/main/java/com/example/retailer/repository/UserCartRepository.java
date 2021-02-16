package com.example.retailer.repository;

import com.example.retailer.entity.UserCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCartRepository extends CrudRepository<UserCart, Integer> {
}
