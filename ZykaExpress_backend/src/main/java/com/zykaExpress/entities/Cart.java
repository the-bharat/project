package com.zykaExpress.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "quantity")
	private int quantity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_id",nullable = false)
	private Menu selectedMenu;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id",nullable = false)
	private User currentUser;
	
	public Cart(int quantity, Menu selectedMenu, User currentUser) {
		super();
		this.quantity = quantity;
		this.selectedMenu = selectedMenu;
		this.currentUser = currentUser;
	}
}
