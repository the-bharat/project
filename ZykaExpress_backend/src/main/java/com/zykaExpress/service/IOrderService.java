package com.zykaExpress.service;

import java.util.List;

import com.zykaExpress.dto.FoodOrderDto;

public interface IOrderService {
	String placeOrderForUser(int userId, int addrId, String paymentMode);

	List<FoodOrderDto> getAllOrders();

	List<FoodOrderDto> getAllPendingOrders();

	List<FoodOrderDto> getAllAssignedOrders(int deliveryBoyId);

	void updateOrderStatus(int orderId, String status, int deliveryId);

	List<FoodOrderDto> getMyOrders(int userId);
}
