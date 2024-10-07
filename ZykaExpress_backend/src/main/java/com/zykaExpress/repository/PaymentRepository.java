package com.zykaExpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zykaExpress.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	@Query("Select p from Payment p where p.currentOrder.id=?1")
	Payment findPaymentByOrderId(Integer integer);
}
