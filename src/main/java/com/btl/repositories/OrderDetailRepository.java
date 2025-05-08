package com.btl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btl.entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}