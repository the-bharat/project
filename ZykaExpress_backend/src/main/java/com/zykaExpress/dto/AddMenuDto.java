package com.zykaExpress.dto;

import org.springframework.web.multipart.MultipartFile;

import com.zykaExpress.entities.Menu;
import com.zykaExpress.entities.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMenuDto {
	
	private String menuName;
	private double price;
	private String description;
	private MultipartFile imageName;
	private int restaurent;
	private int catagory;
	private Byte status;
	private String type;

	public static Menu toEntity(AddMenuDto dto) {
		Menu entity = new Menu();
		entity.setProductName(dto.menuName);
		entity.setDescription(dto.description);
		entity.setPrice(dto.price);
		entity.setStatus((byte) 1);
		entity.setType(Type.valueOf(dto.type));
		return entity;
	}
}