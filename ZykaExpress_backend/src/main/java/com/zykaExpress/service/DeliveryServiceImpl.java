package com.zykaExpress.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zykaExpress.entities.FoodOrder;
import com.zykaExpress.entities.OrderStatus;
import com.zykaExpress.entities.Payment;
import com.zykaExpress.entities.PaymentStatus;
import com.zykaExpress.entities.User;
import com.zykaExpress.repository.FoodOrderRepository;
import com.zykaExpress.repository.PaymentRepository;
import com.zykaExpress.repository.UserRepository;

@Service
@Transactional
public class DeliveryServiceImpl implements IDeliveryService {

	@Autowired
	private FoodOrderRepository orderRepo;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PaymentRepository paymentRepo;

	@Override
	public List<FoodOrder> allPlacedOrders() {

		return orderRepo.findByStatus(OrderStatus.PACKING);
	}

	@Override
	public void assignDeliveryBoy(int userId, int orderId) {
		FoodOrder order = orderRepo.findById(orderId).get();
		User user = userRepo.findById(userId).get();
		order.setDeliverboy(user);
	}

	@Override
	public FoodOrder updateStatus(int orderId, String status) {
		FoodOrder order = orderRepo.findById(orderId).get();
		order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
		order.setStatusUpdateDate(LocalDateTime.now());

		if (status.equals("DELIVERED")) {
			order.setDeliveredTime(LocalDateTime.now());
			Payment payment = paymentRepo.findPaymentByOrderId(orderId);
			payment.setPaymentStatus(PaymentStatus.COMPLETED);
		}

		if (status.equals("CANCELLED")) {
			Payment payment = paymentRepo.findPaymentByOrderId(orderId);
			payment.setPaymentStatus(PaymentStatus.REFUNDED);
		}
		return orderRepo.save(order);
	}

}
