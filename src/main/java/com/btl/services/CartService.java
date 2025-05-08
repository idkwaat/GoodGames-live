package com.btl.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btl.entities.Account;
import com.btl.entities.AccountDetails;
import com.btl.entities.Order;
import com.btl.entities.OrderDetail;
import com.btl.entities.OrderStatus;
import com.btl.entities.Product;
import com.btl.models.Item;
import com.btl.repositories.AccountRepository;
import com.btl.repositories.OrderDetailRepository;
import com.btl.repositories.OrderRepository;
import com.btl.repositories.ProductRepository;

@Service
public class CartService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderDetailRepository detailRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public List<Item> addCart(Product product, List<Item> items) {
		var find = false;
		for (Item item : items) {
			if (item.getProductId().equals(product.getProductId())) {
				item.setQuantity(item.getQuantity() + 1);
				item.setTotal(item.getPrice() * item.getQuantity());
				find = true;
				break;
			}
		}
		if (!find) {
			items.add(new Item(product));
		}
		return items;
	}

	public List<Item> removeCart(String productId, List<Item> items) {
	    Iterator<Item> temp = items.iterator();
	    while (temp.hasNext()) {
	        var item = temp.next();
	        if (item.getProductId().equals(productId)) {
	            temp.remove(); // đúng cách
	            break;
	        }
	    }
	    return items;
	}
	public List<Item> updateCart(String[] pids, int[] qtys, List<Item> items){
		for (Item item : items) {
			for (var i=0;i<pids.length;i++) {
				if(item.getProductId().equals(pids[i])) {
					item.setQuantity(qtys[i]);
					item.setTotal(item.getPrice() * item.getQuantity());
				}
			}
		}
		return items;
	}
	
	@Transactional
	public String insertOrder(Order order, List<Item> items)
	{
		String orderId="HD"+(new SimpleDateFormat("ddMMyyhhmm").format(new Date()));
		order.setOrderId(orderId);
		order.setStatus(OrderStatus.ORDER_NEW);
		AccountDetails user = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Account acc=accountRepository.findByUsername(user.getUsername()).get();
		order.setAccount(acc);
		orderRepository.save(order);
		for (Item item : items) {
			OrderDetail detail=new OrderDetail(item.getPrice(), item.getQuantity(), productRepository.findById(item.getProductId()).get(), order);
			detailRepository.save(detail);
		}
		return "Đặt hàng thành công";
	}
	
}