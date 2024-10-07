package com.zykaExpress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zykaExpress.entities.Category;
import com.zykaExpress.entities.Menu;
import com.zykaExpress.entities.Type;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

	@Query("Select m from Menu m where m.category=?1 and m.status=1")
	List<Menu> getMenuList(Category cat);
	
	@Query("Select m from Menu m where m.status=1")
	List<Menu> findAllByStatus();
	
	@Query("Select m from Menu m where m.restaurant.id=?1")
	List<Menu> findAllByResto(Integer restoId);

	@Query("Select m from Menu m where m.type=?1 and m.status=1")
	List<Menu> getMenuListByType(Type type);
	
//	@Query("SELECT m FROM Menu m JOIN m.restaurant u WHERE m.status = 1 AND u.status = 'ACTIVE'")
//	List<Menu> findAllByStatusAndActiveRestaurent();
//	
//	@Query("SELECT m FROM Menu m JOIN FETCH m.restaurant r WHERE m.status = 1 AND r.id IN :restaurantIds")
//	List<Menu> findAllByStatusAndActiveRestaurent(List<Integer> restaurantIds);
}