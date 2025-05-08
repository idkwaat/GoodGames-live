package com.btl.models;

import java.time.LocalDateTime;

public class OrderDetail {
    private String orderId;
    private LocalDateTime orderDate;
    private int status;

    private int productId;
    private int quantity;
    private double price;
    
	public OrderDetail(String orderId, LocalDateTime orderDate, int status, int productId, int quantity, double price) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.status = status;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
    
	
    
}
