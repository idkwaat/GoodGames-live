package com.btl.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="productid")
	private Long productId;
	
	@Column(name="productname", columnDefinition = "nvarchar(200)")
	@NotBlank(message = "Không để trống")
	@Size(min = 5,max = 200,message = "Độ dài từ 5-200 ký tự")
	private String productName;
	
	@Column(name="brand", columnDefinition = "nvarchar(100)")
	@NotBlank(message = "Không để trống")
	private String brand;
	
	@Column(name="price")
	@Min(value = 0,message = "Giá phải >=0")
	private int price;
	
	@Column(name="pictures",length = 500)
	private String pictures;
	
	@Column(name="description", columnDefinition = "nvarchar(max)")
	private String description;
	
	@Column(name="active")
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name = "categoryid", nullable = false)
	private Category category;
	
	@OneToMany(mappedBy = "product")
	private Set<OrderDetail> details=new HashSet<OrderDetail>();
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Category getCategory() {
	    return category;
	}

	public void setCategory(Category category) {
	    this.category = category;
	}

	public Product(String productName, String brand, int price, String description, boolean active) {
	    this.productName = productName;
	    this.brand = brand;
	    this.price = price;
	    this.description = description;
	    this.active = active;
	}


	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Long getProductId() {
	    return productId;
	}

	public void setProductId(Long productId) {
	    this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Set<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<OrderDetail> details) {
		this.details = details;
	}
	
	
	
}