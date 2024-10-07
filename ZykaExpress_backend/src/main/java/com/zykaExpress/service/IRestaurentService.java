package com.zykaExpress.service;

import java.util.List;

import com.zykaExpress.entities.Menu;
import com.zykaExpress.entities.OrderDetails;
import com.zykaExpress.entities.Rating;

public interface IRestaurentService {

	List<OrderDetails> getAllPlacedOrders(int restId);
	
	List<OrderDetails> getAllAcceptedOrders(int restId);

	List<Rating> getAllRatings(int restId);

	List<Menu> getAllMenus(int restId);

}
