package com.btl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.btl.models.OrderDetail;
import com.btl.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    public void updateStatus(String orderId, int status) {
        String sql = "UPDATE orders SET status = ? WHERE orderid = ?";
        jdbcTemplate.update(sql, status, orderId);
    }



    public List<OrderDetail> getAllOrdersWithDetails() {
        String sql = "SELECT o.orderid, o.orderdate, o.status, od.prouctid, od.quantity, od.price " +
                     "FROM orders o JOIN orderdetails od ON o.orderid = od.orderid";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new OrderDetail(
                rs.getString("orderid"),
                rs.getTimestamp("orderdate").toLocalDateTime(),
                rs.getInt("status"),
                rs.getInt("prouctid"),
                rs.getInt("quantity"),
                rs.getDouble("price")
            );
        });
    }
}
