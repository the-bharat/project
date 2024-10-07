package com.zykaExpress.dto;

import java.util.List;

import com.zykaExpress.entities.FoodOrder;
import com.zykaExpress.entities.OrderDetails;
import com.zykaExpress.entities.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrderDto {

	private FoodOrder order;
	private List<OrderDetails> orderDetails;
	private Payment payment;

	public FoodOrderDto(FoodOrder order, Payment payment) {
		super();
		this.order = order;
		this.payment = payment;
	}
}
