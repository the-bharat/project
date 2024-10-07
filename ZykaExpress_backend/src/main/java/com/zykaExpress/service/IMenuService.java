package com.zykaExpress.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zykaExpress.dto.MenuDto;
import com.zykaExpress.entities.Menu;

public interface IMenuService 
{
	Menu addMenu(Menu menu, MultipartFile multipartFile);
	
	Menu editMenu(MenuDto menuDto, int id);
	
	String deleteMenu(int id);
	
	public List<Menu> findAll();

	List<Menu> findByCategory(int id);

	List<Menu> findByResto(int id);

	List<Menu> findByType(String type);
	
}
