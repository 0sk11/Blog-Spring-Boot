package com.example.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Category;
import com.example.blog.exceptions.ResourceNotFoundExecption;
import com.example.blog.payloads.CategoryDto;
import com.example.blog.repositories.CategoryRepo;
import com.example.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category addedCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Category category = this.categoryRepo.findById(id).orElseThrow(()->new 
				ResourceNotFoundExecption("Category", "Category ID", id));
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		this.categoryRepo.save(category);
		return this.modelMapper.map(this.categoryRepo.save(category), CategoryDto.class);
	}

	@Override
	public void DeleteCategory(Integer id) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(id).orElseThrow(()->new 
				ResourceNotFoundExecption("Category", "Category ID", id));
		this.categoryRepo.delete(category);

	}

	@Override
	public CategoryDto getCategory(Integer id) {
		Category category = this.categoryRepo.findById(id).orElseThrow(()->new 
				ResourceNotFoundExecption("Category", "Category ID", id));
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> categoriesDto = categories.stream().map((category)->this.modelMapper.map(categories, CategoryDto.class))
		.collect(Collectors.toList());
		return categoriesDto;
	}

}
