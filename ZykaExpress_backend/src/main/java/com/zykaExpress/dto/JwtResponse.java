package com.zykaExpress.dto;

import com.zykaExpress.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

	private User user;
	private String token;
}
