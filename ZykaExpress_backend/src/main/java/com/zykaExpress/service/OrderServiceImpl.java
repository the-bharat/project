package com.zykaExpress.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zykaExpress.dto.FoodOrderDto;
import com.zykaExpress.entities.Address;
import com.zykaExpress.entities.Cart;
import com.zykaExpress.entities.FoodOrder;
import com.zykaExpress.entities.OrderDetails;
import com.zykaExpress.entities.OrderStatus;
import com.zykaExpress.entities.Payment;
import com.zykaExpress.entities.PaymentModes;
import com.zykaExpress.entities.PaymentStatus;
import com.zykaExpress.entities.User;
import com.zykaExpress.repository.AddressRepository;
import com.zykaExpress.repository.CartRepository;
import com.zykaExpress.repository.FoodOrderRepository;
import com.zykaExpress.repository.OrderDetailsRepository;
import com.zykaExpress.repository.PaymentRepository;
import com.zykaExpress.repository.UserRepository;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private FoodOrderRepository foodOrderRepo;

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private OrderDetailsRepository orderDetailsRepo;

	@Override
	public String placeOrderForUser(int userId, int addrId, String paymentMode) {

		// get all cart items for given user
		List<Cart> cartItems = cartRepo.findAllItemsByUser(userId);

		double total = 0.0;
		int deliveryCharges = 50;
		for (Cart item : cartItems) {
			total += item.getQuantity() * item.getSelectedMenu().getPrice();
		}
		
		if(total>=500) {
			deliveryCharges = 0;
		}

		Address address = addressRepo.findbyId(addrId);
		User customer = userRepo.findByUserId(userId);

		FoodOrder newOrder = new FoodOrder(total, OrderStatus.PLACED, LocalDateTime.now(), LocalDateTime.now(),
				customer, null, address);
		foodOrderRepo.save(newOrder);

		Payment payment = new Payment(total + deliveryCharges,
				paymentMode.equals("COD") ? PaymentStatus.PENDING : PaymentStatus.COMPLETED, LocalDateTime.now(),
				PaymentModes.valueOf(paymentMode), newOrder);
		paymentRepo.save(payment);
		cartItems.forEach(item -> {

			orderDetailsRepo.save(new OrderDetails(item.getQuantity(), item.getSelectedMenu().getPrice(), newOrder,
					item.getSelectedMenu()));
		});
		cartRepo.deleteAll(cartItems);
		return "Order Placed Successfully!";
	}

	@Override
	public List<FoodOrderDto> getAllOrders() {
		List<FoodOrder> orders = foodOrderRepo.findAll();
		List<FoodOrderDto> response = new ArrayList<>();

		for (FoodOrder order : orders) {
			List<OrderDetails> orderDetails = orderDetailsRepo.findAllByOrderId(order.getId());
			Payment payment = paymentRepo.findPaymentByOrderId(order.getId());
			response.add(new FoodOrderDto(order, orderDetails, payment));
		}
		return response;
	}

	@Override
	public List<FoodOrderDto> getAllPendingOrders() {
		List<FoodOrder> orders = foodOrderRepo.findAll();
		List<FoodOrderDto> response = new ArrayList<>();

		for (FoodOrder order : orders) {
			List<OrderDetails> orderDetails = orderDetailsRepo.findAllByOrderId(order.getId());

			String s = order.getStatus().toString();

			if (s.equals("OUT_FOR_DELIVERY") || s.equals("PLACED")) {
				Payment payment = paymentRepo.findPaymentByOrderId(order.getId());
				response.add(new FoodOrderDto(order, orderDetails, payment));
			}
		}
		return response;
	}

	@Override
	public void updateOrderStatus(int orderId, String status, int deliveryId) {
		FoodOrder order = foodOrderRepo.findById(orderId).get();
		order.setStatus(OrderStatus.valueOf(status));
		order.setStatusUpdateDate(LocalDateTime.now());
		User deliverBoy = userRepo.findById(deliveryId).get();
		order.setDeliverboy(deliverBoy);
		if (status.equals("DELIVERED")) {
			Payment payment = paymentRepo.findPaymentByOrderId(orderId);
			payment.setPaymentStatus(PaymentStatus.COMPLETED);
		}

	}

	@Override
	public List<FoodOrderDto> getMyOrders(int userId) {
		List<FoodOrder> orders = foodOrderRepo.findAllOrdersByUserId(userId);

		List<FoodOrderDto> response = new ArrayList<>();

		for (FoodOrder order : orders) {
			List<OrderDetails> orderDetails = orderDetailsRepo.findAllByOrderId(order.getId());
			Payment payment = paymentRepo.findPaymentByOrderId(order.getId());
			response.add(new FoodOrderDto(order, orderDetails, payment));
		}
		return response;
	}

	@Override
	public List<FoodOrderDto> getAllAssignedOrders(int deliveryBoyId) {
		List<FoodOrder> orders = foodOrderRepo.findAllOrdersByDeliverBoyId(deliveryBoyId);

		List<FoodOrderDto> response = new ArrayList<>();

		for (FoodOrder order : orders) {
			List<OrderDetails> orderDetails = orderDetailsRepo.findAllByOrderId(order.getId());
			Payment payment = paymentRepo.findPaymentByOrderId(order.getId());
			response.add(new FoodOrderDto(order, orderDetails, payment));
		}
		return response;
	}
}