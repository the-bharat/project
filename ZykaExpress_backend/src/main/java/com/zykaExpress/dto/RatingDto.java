package com.zykaExpress.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {

	private int rating;
	private String comment;
	private int restId;
	private int userId;
}