package com.zykaExpress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zykaExpress.custom_excpetions.ResourceNotFoundException;
import com.zykaExpress.dto.MenuDto;
import com.zykaExpress.entities.Category;
import com.zykaExpress.entities.Menu;
import com.zykaExpress.entities.Type;
import com.zykaExpress.repository.CategoryRepository;
import com.zykaExpress.repository.MenuRepository;
import com.zykaExpress.repository.UserRepository;

@Service
@Transactional
public class MenuServiceImpl implements IMenuService {

	@Autowired
	MenuRepository menuRepo;

	@Autowired
	ImageServiceImpl imageService;

	@Autowired
	CategoryRepository catRepo;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Menu addMenu(Menu menu, MultipartFile imageName) {
		String image = imageService.store(imageName);
		menu.setImage(image);

		return menuRepo.save(menu);
	}

	@Override
	public Menu editMenu(MenuDto menuDto, int id) {
		Menu menu = getMenuDetails(id);
		menu.setDescription(menuDto.getDescription());
		menu.setPrice(menuDto.getPrice());
		menu.setStatus(menuDto.getStatus());
		return menuRepo.save(menu);
	}

	public Menu getMenuDetails(int id) {
		return menuRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu Not Found !!!"));
	}

	@Override
	public String deleteMenu(int id) {
		menuRepo.deleteById(id);
		return "Menu Deleted successfully";
	}

	@Override
	public List<Menu> findAll() {

		return menuRepo.findAllByStatus();
		
//		List<User> u = userRepository.findByActiveRestaurent();
//		
//		List<Integer> ilist = new ArrayList<>();
//		
//		for (User user : u) {
//			ilist.add(user.getId());
//		}
//		
//		return menuRepo.findAllByStatusAndActiveRestaurent(ilist);
	}

	@Override
	public List<Menu> findByCategory(int id) {
		Category cat = catRepo.findById(id).get();
		List<Menu> menulist = menuRepo.getMenuList(cat);
		return menulist;
	}

	@Override
	public List<Menu> findByResto(int id) {

		List<Menu> menulist = menuRepo.findAllByResto(id);
		return menulist;
	}

	@Override
	public List<Menu> findByType(String type) {
		List<Menu> menulist = menuRepo.getMenuListByType(Type.valueOf(type));
		return menulist;
	}
}
