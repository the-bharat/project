package com.zykaExpress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zykaExpress.entities.Menu;
import com.zykaExpress.entities.OrderDetails;
import com.zykaExpress.entities.OrderStatus;
import com.zykaExpress.entities.Payment;
import com.zykaExpress.entities.Rating;
import com.zykaExpress.repository.MenuRepository;
import com.zykaExpress.repository.OrderDetailsRepository;
import com.zykaExpress.repository.PaymentRepository;
import com.zykaExpress.repository.RatingRepository;

@Service
@Transactional
public class RestaurentServiceImpl implements IRestaurentService {
	@Autowired
	OrderDetailsRepository orderDetailsRepo;

	@Autowired
	RatingRepository ratingRepo;

	@Autowired
	MenuRepository menuRepo;

	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public List<OrderDetails> getAllPlacedOrders(int restId) {
		return orderDetailsRepo.getPlacedOrders(OrderStatus.PLACED, restId);
	}

	@Override
	public List<OrderDetails> getAllAcceptedOrders(int restId) {

		return orderDetailsRepo.getAcceptedOrders(OrderStatus.PLACED, restId);
	}

	@Override
	public List<Rating> getAllRatings(int restId) {

		return ratingRepo.findAllByRestaurant(restId);
	}

	@Override
	public List<Menu> getAllMenus(int restoId) {

		return menuRepo.findAllByResto(restoId);
	}

	public List<Payment> getAllAcceptedOrdersPayment(int restId) {

		return paymentRepository.findAll();
	}
}
