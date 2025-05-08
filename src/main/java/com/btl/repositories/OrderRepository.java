package com.btl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.btl.entities.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

}