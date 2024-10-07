package com.zykaExpress.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zykaExpress.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>{
	
	Optional<Rating> findById(Integer userid);

	@Query("select r from Rating r where r.selectedRestaurant.id=?1")
	List<Rating> findAllByRestaurant(Integer restId);
}
